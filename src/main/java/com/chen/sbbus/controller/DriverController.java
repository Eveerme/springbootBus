package com.chen.sbbus.controller;

import com.chen.sbbus.entity.*;
import com.chen.sbbus.service.*;
import com.chen.sbbus.utils.*;
import com.chen.sbbus.utils.MQTT.MQTTUtils;
import com.chen.sbbus.utils.Re.DriverLoginResponse;
import com.chen.sbbus.utils.Re.LoginRequest;
import com.chen.sbbus.utils.Re.LoginResponse;
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
            // 更新登录状态
            DriverInfo driverInfo = driverService.getDriverInfoByAccount(account);
            if (driverService.getDriverIsOnlineById(driverInfo.getId())==1){
                //账号有其他设备同时在线，无法同时登录
                return new LoginResponse(false,"账号有其他设备同时在线，请退出其它在线后再尝试登录",null,null);
            }
            //获取系统时间
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date = new Date(System.currentTimeMillis());
            System.out.println(formatter.format(date));
            GregorianCalendar calendar = new GregorianCalendar();
            //int nTime = calendar.get(Calendar.HOUR_OF_DAY);
            int nTime = 8;
            List<Schedule> scheduleList = scheduleService.getScheduleListByDriverId(driverInfo.getId());
            Schedule sc = scheduleList.get(0);
            if (sc.getIsPermit()==0){
                //不允许发车，不可以登录
                return new LoginResponse(false,"禁止登录！请先扫码签到！",null,null);
            }
            //允许登录的情况,先插入到历史表，记录hId
            String token = JWTUtils.getToken(request);
            driverService.updateDriverIsOnline(driverInfo.getId(),1);

            Integer historyId=0;
            History history = new History();
            history.setScheduleId(sc.getId());
            history.setSTime(formatter.format(date));
            history.setCTime(formatter.format(date));
            historyService.insertHistory(history);
            historyId = history.getId();

            scheduleService.setIsDone(sc.getId());
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
            scheduleInfo.setStart(stationInfos.get(0).getStationName());
            scheduleInfo.setEnd(stationInfos.get(stationInfos.size()-1).getStationName());
            scheduleInfo.setStationInfos(stationInfos);
            scheduleInfo.setStationNum(stations.size());
            scheduleInfo.setHistoryId(historyId);
            return new LoginResponse(true,token, driverInfo,scheduleInfo);
        }
        else{
            return new LoginResponse(false,null,null,null);
        }
    }
    @PostMapping("/driverLogin")
    public DriverLoginResponse driverLogin(@RequestBody LoginRequest request){
        // verify username and password, and return token and user info if valid
        //验证用户名和密码，并返回token和用户信息（如果有效）
        String account = request.getAccount();
        String password = request.getPassword();
        if (driverService.login(account, password)){
            //信息验证成功，返回部分用户信息
            String token = JWTUtils.getToken(request);
            // 更新登录状态
            DriverInfo driverInfo = driverService.getDriverInfoByAccount(account);
            if (driverService.getDriverIsOnlineById(driverInfo.getId())==1){
                //账号有其他设备同时在线，无法同时登录
                return new DriverLoginResponse(false,"账号有其他设备同时在线，请退出其它在线后再尝试登录",null);
            }
            driverService.updateDriverIsOnline(driverInfo.getId(),1);
            List<ScheduleInfo> scheduleInfoList = new ArrayList<>();
            List<Schedule> scheduleList = scheduleService.getScheduleIsNotDone(driverInfo.getId());
            //更新调度表
            for (Schedule schedule:scheduleList){
                //获取系统时间
                SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date date = new Date(System.currentTimeMillis());
                System.out.println(formatter.format(date));
                GregorianCalendar calendar = new GregorianCalendar();
                //int nTime = calendar.get(Calendar.HOUR_OF_DAY);
                int sTime = schedule.getStartTime();
                int dTime = schedule.getDTime();
                int nTime = 8;
                if (sTime <= nTime && nTime < sTime+dTime){
                    //该调度在发车时间段内
                    break;
                }else if (nTime >= sTime+dTime){
                    //该调度在发车时间段后
                    //该调度已作废，填入历史记录表，记录为未完成
                    History history = new History();
                    history.setScheduleId(schedule.getId());
                    history.setCTime(formatter.format(date));
                    historyService.insertHistory(history);
                    //同时设置is_done为1
                    scheduleService.setIsDone(schedule.getId());

                }else {
                    //该调度在发车时间段前
                    break;
                }
            }

            return new DriverLoginResponse(true,token, driverInfo);
        }
        else{
            return new DriverLoginResponse(false,null,null);
        }
    }
    /*
    * id:调度表的id
    * hid:历史表的id
    * */
    @GetMapping("/logout/{id}/{hId}")
    public R logout(@PathVariable("id") Integer id,@PathVariable("hId") Integer hId){
        Schedule schedule = scheduleService.getById(id);
        //设置离线状态
        driverService.updateDriverIsOnline(schedule.getDriverId(),0);
        String busId = schedule.getBusId();
        String topic = "/bus/"+busId+"/pub_topic";
        String subTopic = "/bus/"+schedule.getBusId()+"/sub_topic";
        String msg = "0";
        mqttUtils.publishMsg(subTopic,msg,2);
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
    /*
    * 扫码签到
    * */
    @GetMapping("/signIn/{bId}/{sId}/{dId}")
    public R signIn(@PathVariable("bId") Integer bId,@PathVariable("sId") Integer sId,@PathVariable("dId") Integer dId){
        Schedule schedule = scheduleService.getById(sId);
        if (schedule.getDriverId()!=dId){
            return new R(false,"扫码错误，不是此辆车！");
        }
        if (schedule.getIsPermit()==1){
            return new R(false,"签到错误，已经签过到了！");
        }
        //获取系统时间
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date));
        GregorianCalendar calendar = new GregorianCalendar();
        //int nTime = calendar.get(Calendar.HOUR_OF_DAY);
        int sTime = schedule.getStartTime();
        int dTime = schedule.getDTime();
        int nTime = 8;
        if (sTime <= nTime && nTime < sTime+dTime){
            //在发车时间段内，可以发车
            scheduleService.setIsPermitById(sId,1);
            //发送命令给MQTT告知设备允许发车
            String topic = "/bus/"+schedule.getBusId()+"/sub_topic";
            String msg = "1";
            mqttUtils.publishMsg(topic,msg,2);
            //返回给小程序签到成功，允许发车
            return new R(true,"签到成功，允许发车");
        }else {
            //不在发车时间段内，不允许发车
            return new R(false,"不在签到时间");
        }
    }
    @GetMapping("/DriverLogout/{id}")
    public R logout(@PathVariable("id") Integer id){
        //设置离线状态
        driverService.updateDriverIsOnline(id,0);
        return new R(true);
    }
    /*
    * 获取未完成调度的信息
    * */
    @GetMapping("/getScheduleInfoList/{dId}")
    public R getScheduleInfoList(@PathVariable("dId") Integer dId){
        List<ScheduleInfo> scheduleInfoList = new ArrayList<>();
        List<Schedule> scheduleList = scheduleService.getScheduleIsNotDone(dId);

        for (Schedule schedule:scheduleList){
            ScheduleInfo scheduleInfo = new ScheduleInfo();
            scheduleInfo.setSchedule(schedule);
            List<StationInfo> stationInfos = new ArrayList<>();
            //获取线路
            Routes route = routeService.getRoutesById(schedule.getRouteId());
            List<String> stations = route.getStationsList();

            for (String stationId:stations) {
                StationInfo stationInfo = new StationInfo();
                stationInfo.setStationId(stationId);
                Station station = stationService.getStationById(stationId);
                stationInfo.setStationName(station.getName());
                stationInfos.add(stationInfo);
            }
            scheduleInfo.setStart(stationInfos.get(0).getStationName());
            scheduleInfo.setEnd(stationInfos.get(stationInfos.size()-1).getStationName());
            scheduleInfo.setStationInfos(stationInfos);
            scheduleInfo.setStationNum(stations.size());
            scheduleInfoList.add(scheduleInfo);
        }
        return new R(true,scheduleInfoList);
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
