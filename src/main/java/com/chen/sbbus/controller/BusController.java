package com.chen.sbbus.controller;

import com.chen.sbbus.entity.Bus;
import com.chen.sbbus.service.impl.BusServiceImpl;
import com.chen.sbbus.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bus")
public class BusController {

    @Autowired
    private BusServiceImpl busService;

    //查询所有Bus
    @GetMapping
    public R getAllBus(){
        return new R(true,busService.findAllBus());
    }
    @GetMapping("/{id}")
    public R getBusById(@PathVariable("id") Integer id){
        return new R(true,busService.getBusById(id));
    }
    //插入新的bus信息
    @PostMapping("/insert")
    public R insertBusInfo(@RequestBody Bus bus){
        busService.insertBus(bus);
        return new R(true);
    }
    //根据id更新bus信息
    @PostMapping("/update")
    public R updateBusInfo(@RequestBody Bus bus){
        busService.updateBus(bus);
        return new R(true);
    }
}
