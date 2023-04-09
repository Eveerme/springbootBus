package com.chen.sbbus.controller;

import com.chen.sbbus.service.DriverService;
import com.chen.sbbus.utils.DriverInfo;
import com.chen.sbbus.utils.JWTUtils;
import com.chen.sbbus.utils.LoginRequest;
import com.chen.sbbus.utils.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;

@RestController
@RequestMapping("/driver")
public class DriverController {
    @Autowired
    private DriverService driverService;
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request){
        // verify username and password, and return token and user info if valid
        //验证用户名和密码，并返回token和用户信息（如果有效）
        String account = request.getAccount();
        String password = request.getPassword();
        if (driverService.login(account, password)){
            //信息验证成功，返回部分用户信息
            String token = JWTUtils.getToken(request);
            DriverInfo driverInfo = driverService.getDriverInfoByAccount(account);
            return new LoginResponse(true,token, driverInfo);
        }
        else{
            return new LoginResponse(false,null,null);
        }
    }

}
