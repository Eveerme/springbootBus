package com.chen.sbbus.controller;

import com.chen.sbbus.entity.Driver;
import com.chen.sbbus.entity.Route;
import com.chen.sbbus.entity.Schedule;
import com.chen.sbbus.service.DriverService;
import com.chen.sbbus.service.RouteService;
import com.chen.sbbus.service.ScheduleService;
import com.chen.sbbus.service.StationService;
import com.chen.sbbus.utils.*;
import com.chen.sbbus.utils.MQTT.MQTTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
            //获取调度busId
            Schedule schedule = scheduleService.selectBusIdByDriverId(driverInfo.getId());
            String busId = schedule.getBusId();
            //获取线路
            Route route = routeService.getById(schedule.getRouteId());

            driverInfo.setBusId(busId);
            //登录成功调用订阅mqtt相关服务
            String topic = "/bus/"+busId+"/pub_topic";
            mqttUtils.subscribeTopic(topic,2);
            //获取线路
            ScheduleInfo scheduleInfo = new ScheduleInfo();
            scheduleInfo.setSchedule(schedule);
            List<StationInfo> stationInfos = new ArrayList<>();
            int num = 0;
            if (route.getR1()!=null){
                StationInfo stationInfo = new StationInfo();
                stationInfo.setStationId(route.getR1());
                stationInfo.setStationName(stationService.getStationById(route.getR1()).getName());
                //设置起始车站
                scheduleInfo.setStart(stationInfo.getStationName());
                stationInfos.add(stationInfo);
                num++;
            }
            if (route.getR2()!=null){
                StationInfo stationInfo = new StationInfo();
                stationInfo.setStationId(route.getR2());
                stationInfo.setStationName(stationService.getStationById(route.getR2()).getName());
                stationInfos.add(stationInfo);
                num++;
            }
            if (route.getR3()!=null){
                StationInfo stationInfo = new StationInfo();
                stationInfo.setStationId(route.getR3());
                stationInfo.setStationName(stationService.getStationById(route.getR3()).getName());
                stationInfos.add(stationInfo);
                num++;
            }
            if (route.getR4()!=null){
                StationInfo stationInfo = new StationInfo();
                stationInfo.setStationId(route.getR4());
                stationInfo.setStationName(stationService.getStationById(route.getR4()).getName());
                stationInfos.add(stationInfo);
                num++;
            }
            if (route.getR5()!=null){
                StationInfo stationInfo = new StationInfo();
                stationInfo.setStationId(route.getR5());
                stationInfo.setStationName(stationService.getStationById(route.getR5()).getName());
                stationInfos.add(stationInfo);
                num++;
            }
            if (route.getR6()!=null){
                StationInfo stationInfo = new StationInfo();
                stationInfo.setStationId(route.getR6());
                stationInfo.setStationName(stationService.getStationById(route.getR6()).getName());
                stationInfos.add(stationInfo);
                num++;
            }
            if (route.getR7()!=null){
                StationInfo stationInfo = new StationInfo();
                stationInfo.setStationId(route.getR7());
                stationInfo.setStationName(stationService.getStationById(route.getR7()).getName());
                stationInfos.add(stationInfo);
                num++;
            }
            if (route.getR8()!=null){
                StationInfo stationInfo = new StationInfo();
                stationInfo.setStationId(route.getR8());
                stationInfo.setStationName(stationService.getStationById(route.getR8()).getName());
                stationInfos.add(stationInfo);
                num++;
            }
            if (route.getR9()!=null){
                StationInfo stationInfo = new StationInfo();
                stationInfo.setStationId(route.getR9());
                stationInfo.setStationName(stationService.getStationById(route.getR9()).getName());
                stationInfos.add(stationInfo);
                num++;
            }
            if (route.getR10()!=null){
                StationInfo stationInfo = new StationInfo();
                stationInfo.setStationId(route.getR10());
                stationInfo.setStationName(stationService.getStationById(route.getR10()).getName());
                stationInfos.add(stationInfo);
                num++;
            }
            scheduleInfo.setEnd(stationService.getStationById(route.getEnd()).getName());
            scheduleInfo.setStationInfos(stationInfos);
            scheduleInfo.setStationNum(num);
            return new LoginResponse(true,token, driverInfo,scheduleInfo);
        }
        else{
            return new LoginResponse(false,null,null,null);
        }
    }
    @GetMapping("/logout/{id}")
    public R logout(@PathVariable("id") Integer id){
        driverService.updateDriverIsOnline(id,0);
        Schedule schedule = scheduleService.selectBusIdByDriverId(id);
        String busId = schedule.getBusId();
        String topic = "/bus/"+busId+"/pub_topic";
        //退出登录后退订相关主题
        mqttUtils.unSubscribeTopic(topic);
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
