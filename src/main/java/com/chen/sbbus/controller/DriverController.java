package com.chen.sbbus.controller;

import com.chen.sbbus.entity.Driver;
import com.chen.sbbus.service.DriverService;
import com.chen.sbbus.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

/*
*   lo:11.213 E_W:E //经度
*   la:72.3  N_S:S//纬度
* */
