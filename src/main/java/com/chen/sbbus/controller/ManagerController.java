package com.chen.sbbus.controller;

import com.chen.sbbus.entity.Driver;
import com.chen.sbbus.entity.Schedule;
import com.chen.sbbus.service.DriverService;
import com.chen.sbbus.service.ManageService;
import com.chen.sbbus.service.ScheduleService;
import com.chen.sbbus.utils.JWTUtils;
import com.chen.sbbus.utils.MQTT.MQTTUtils;
import com.chen.sbbus.utils.Re.ManagerLoginRequest;
import com.chen.sbbus.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manage")
public class ManagerController {
    @Autowired
    private ManageService manage;
    @Autowired
    private DriverService driverService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private MQTTUtils mqttUtils;

    @PostMapping("/login")
    public R login(@RequestBody ManagerLoginRequest request){

        String account = request.getAccount();
        String password = request.getPassword();
        if(manage.login(account,password)!=null){
            String token = JWTUtils.getMToken(request);
            //登录成功后订阅相关的主题
//            List<Driver> driverList = driverService.selectDriverOnline();
//            for (Driver driver:driverList){
//                Schedule schedule = scheduleService.selectScheduleByDriverId(driver.getId());
//                String busId = schedule.getBusId();
//                String topic = "/bus/"+busId+"/pub_topic";
//                mqttUtils.subscribeTopic(topic, 2);
//                System.out.println("订阅主题:"+topic);
//            }
            return new R(true,token);
        }
        else{
            return new R(false);
        }
    }

}
