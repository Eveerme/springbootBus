package com.chen.sbbus.controller;

import com.chen.sbbus.entity.Routes;
import com.chen.sbbus.entity.Schedule;
import com.chen.sbbus.service.RouteService;
import com.chen.sbbus.service.ScheduleService;
import com.chen.sbbus.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private RouteService routeService;

    //查询所有用户
    @GetMapping
    public R getAllSchedule(){
        return new R(true,scheduleService.list());
    }

    @GetMapping("/stationNum/{id}")
    public R getStationNum(@PathVariable("id") Integer id){
        Routes routes = routeService.getRoutesById(id);
        List<String> stationList = routes.getStationsList();
        return new R();
    }

    //根据用户查询Id
    @GetMapping("/{id}")
    public R getAllScheduleById(@PathVariable("id") Integer id){
        return new R(true,scheduleService.getById(id));
    }

    //根据Id删除用户
    @DeleteMapping("/delete/{id}")
    public R deleteScheduleById(@PathVariable Integer id){
        return new R(scheduleService.removeById(id));
    }

    //新增用户
    @PostMapping("/insert")
    public R insertSchedule(@RequestBody Schedule schedule){
        return new R(scheduleService.insertSchedule(schedule));
    }
    //新更新用户
    @PostMapping("/update")
    public R updateSchedule(@RequestBody Schedule schedule){
        return new R(scheduleService.updateSchedule(schedule));
    }


    //分页查询接口
    //接口路径： 相当于 /user/page?currentPage=1&pageSize=5
    //使用@RequestParam接收
    @GetMapping("/page")
    public R getAllScheduleByPage(@RequestParam("currentPage")int currentPage,
                               @RequestParam("pageSize")int pageSize){
        return new R(true,scheduleService.getScheduleByPage(currentPage, pageSize));
    }

}
