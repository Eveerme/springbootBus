package com.chen.sbbus.controller;

import com.chen.sbbus.entity.*;
import com.chen.sbbus.service.*;
import com.chen.sbbus.utils.*;
import com.chen.sbbus.utils.MQTT.MQTTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/driver")
public class DriverController {
    @Autowired
    private DriverService driverService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private RouteService routeService;
    @Autowired
    private StationService stationService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private WarnService warnService;
    @Autowired
    private MQTTUtils mqttUtils;
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request){
        // verify username and password, and return token and user info if valid
        //验证用户名和密码，并返回token和用户信息（如果有效）
        String account = request.getAccount();
        String password = request.getPassword();
        if (driverService.login(account, password)){
            //信息验证成功，返回部分用户信息
            String token = JWTUtils.getToken(request);
            // 更新登录状态
            DriverInfo driverInfo = driverService.getDriverInfoByAccount(account);
            driverService.updateDriverIsOnline(driverInfo.getId(),1);

            //获取调度Schedule
            //获取系统时间
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date = new Date(System.currentTimeMillis());
            System.out.println(formatter.format(date));
            GregorianCalendar calendar = new GregorianCalendar();
            //int nTime = calendar.get(Calendar.HOUR_OF_DAY);
            int nTime = 9;
            List<Schedule> scheduleList = scheduleService.getScheduleListByDriverId(driverInfo.getId());
            Schedule sc = new Schedule();
            Integer historyId=0;
            for (Schedule schedule:scheduleList){
                int sTime = schedule.getStartTime();
                int dTime = schedule.getDTime();
                if (sTime <= nTime){
                    //登录时间比发车时间晚
                    if (sTime + dTime > nTime){
                        //在发车时间内，正常发车
                        //返回司机调度
                        sc = schedule;
                        History history = new History();
                        history.setScheduleId(schedule.getId());
                        history.setSTime(formatter.format(date));
                        history.setCTime(formatter.format(date));
                        historyService.insertHistory(history);
                        historyId = history.getId();
                        scheduleService.setIsDone(schedule.getId());
                        break;

                    }else {
                        //不在发车时间内
                        //当前找到的该司机最早的调度记录已超时,此时系统判断下一条调度表的记录
                        //记录到警告表中,msg：发车时间超时，且未完成该调度
                        Warn warn = new Warn();
                        warn.setScheduleId(schedule.getId());
                        warn.setMsg("发车时间超时，且未完成该调度");
                        warnService.insertWarn(warn);
                        scheduleService.setIsDone(schedule.getId());
                        //插入到历史记录表中
                        History history = new History();
                        history.setScheduleId(schedule.getId());
                        history.setCTime(formatter.format(date));
                        historyService.insertHistoryAll(history);
                        historyId = history.getId();
                    }

                }else {
                    //登录时间比发车时间早
                    //返回司机调度
                    sc = schedule;
                    History history = new History();
                    history.setScheduleId(schedule.getId());
                    history.setSTime(formatter.format(date));
                    history.setCTime(formatter.format(date));
                    historyService.insertHistory(history);
                    historyId = history.getId();
                    //记录到警告表中,msg：发车时间过早，时间
                    Warn warn = new Warn();
                    warn.setScheduleId(schedule.getId());
                    warn.setMsg("发车时间过早,时间：" + formatter.format(date));
                    warnService.insertWarn(warn);
                    break;
                }

            }
            String busId = sc.getBusId();
            //获取线路
            Routes route = routeService.getRoutesById(sc.getRouteId());
            List<String> stations = route.getStationsList();


            driverInfo.setBusId(busId);
            //登录成功调用订阅mqtt相关服务
            String topic = "/bus/"+busId+"/pub_topic";
            mqttUtils.subscribeTopic(topic,2);

            ScheduleInfo scheduleInfo = new ScheduleInfo();
            //把线路信息写入到scheduleInfo中
            List<StationInfo> stationInfos = new ArrayList<>();
            for (String stationsId:stations){
                StationInfo stationInfo = new StationInfo();
                stationInfo.setStationId(stationsId);
                Station station = stationService.getStationById(stationsId);
                stationInfo.setStationName(station.getName());
                stationInfos.add(stationInfo);
            }
            scheduleInfo.setSchedule(sc);

            scheduleInfo.setStationInfos(stationInfos);
            scheduleInfo.setStationNum(stations.size());
            scheduleInfo.setHistoryId(historyId);
            return new LoginResponse(true,token, driverInfo,scheduleInfo);
        }
        else{
            return new LoginResponse(false,null,null,null);
        }
    }
    @GetMapping("/logout/{id}/{hId}")
    public R logout(@PathVariable("id") Integer id,@PathVariable("hId") Integer hId){
        driverService.updateDriverIsOnline(id,0);
        Schedule schedule = scheduleService.getById(id);
        String busId = schedule.getBusId();
        String topic = "/bus/"+busId+"/pub_topic";
        //退出登录后退订相关主题
        mqttUtils.unSubscribeTopic(topic);
        //记录到历史记录表中
        //获取系统时间
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date));
        historyService.updateHistoryById(hId,formatter.format(date));
        return new R(true);
    }
    @GetMapping
    public R getAllDriver(){
        return new R(true,driverService.list());
    }

    //根据用户查询Id
    @GetMapping("/{id}")
    public R getAllDriverById(@PathVariable("id") String id){
        return new R(true,driverService.getById(id));
    }

    //根据Id删除用户
    @GetMapping("/delete")
    public R deleteDriverById(@RequestParam("id") Integer id){
        return new R(driverService.removeById(id));
    }

    //新增用户
    @PostMapping("/insert")
    public R insertDriver(@RequestBody Driver driver){
        return new R(driverService.
                insertDriver(driver));
    }
    //新更新用户
    @PostMapping("/update")
    public R updateDriver(@RequestBody Driver driver){
        return new R(driverService.updateDriver(driver));
    }


    //分页查询接口
    //接口路径： 相当于 /user/page?currentPage=1&pageSize=5
    //使用@RequestParam接收
    @GetMapping("/page")
    public R getAllDriverByPage(@RequestParam("currentPage")int currentPage,
                               @RequestParam("pageSize")int pageSize,
                               @RequestParam("name")String name){
        return new R(true,driverService.getDriverByPage(currentPage, pageSize,name));
    }

}
