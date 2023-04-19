package com.chen.sbbus.controller;

import com.chen.sbbus.service.ManageService;
import com.chen.sbbus.service.impl.ManageServiceImpl;
import com.chen.sbbus.utils.JWTUtils;
import com.chen.sbbus.utils.MQTT.MQTTUtils;
import com.chen.sbbus.utils.ManagerLoginRequest;
import com.chen.sbbus.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manage")
public class ManagerController {
    @Autowired
    private ManageService manage;
    @Autowired
    private MQTTUtils mqttUtils;

    @PostMapping("/login")
    public R login(@RequestBody ManagerLoginRequest request){
        String account = request.getAccount();
        String password = request.getPassword();
        if(manage.login(account,password)!=null){
            String token = JWTUtils.getMToken(request);



            return new R(true,token);
        }
        else{
            return new R(false);
        }
    }

    @GetMapping("/test")
    public R testAll(){

        return new R(true);
    }

}
