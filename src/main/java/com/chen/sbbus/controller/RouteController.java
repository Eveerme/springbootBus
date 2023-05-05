package com.chen.sbbus.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chen.sbbus.entity.Routes;
import com.chen.sbbus.service.RouteService;
import com.chen.sbbus.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/route")
public class RouteController {
    @Autowired
    private RouteService routeService;

    //查询所有用户
    @GetMapping
    public R getAllRoute(){
        return new R(true,routeService.list());
    }

    //根据用户查询Id
    @GetMapping("/{id}")
    public R getAllRouteById(@PathVariable("id") Integer id){
        Routes routes = routeService.getRoutesById(id);
        return new R(true,routes);
    }

    //根据Id删除用户
    @GetMapping("/delete")
    public R deleteRouteById(@RequestParam("id") Integer id){
        return new R(routeService.removeById(id));
    }


    //新增用户
    @PostMapping("/insert")
    public R insertRoute(@RequestBody Routes route){
        return new R(routeService.insertRoute(route));
    }
    //新更新用户
    @PostMapping("/update")
    public R updateRoute(@RequestBody Routes route){
        route.toString();
        return new R(routeService.updateRoute(route));
    }


    //分页查询接口
    //接口路径： 相当于 /user/page?currentPage=1&pageSize=5
    //使用@RequestParam接收
    @GetMapping("/pageList")
    public R getAllRouteByPage(@RequestParam("currentPage")int currentPage,
                               @RequestParam("pageSize")int pageSize,
                               @RequestParam("name")String name){
        IPage<Routes> routesByPage = routeService.getRoutesByPage(currentPage, pageSize, name);
        return new R(true,routesByPage);
    }

}
