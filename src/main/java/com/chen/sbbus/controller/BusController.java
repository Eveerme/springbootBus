package com.chen.sbbus.controller;

import com.chen.sbbus.entity.Bus;
import com.chen.sbbus.service.BusService;

import com.chen.sbbus.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bus")
public class BusController {

    @Autowired
    private BusService busService;

    //查询所有Bus
    @GetMapping
    public R getAllBus(){
        return new R(true,busService.findAllBus());
    }
    @GetMapping("/{id}")
    public R getBusById(@PathVariable("id") String id){
        return new R(true,busService.getBusById(id));
    }

    @PutMapping("/insert")
    public R insertBusInfo(@RequestBody Bus bus){//插入新的bus信息

        return new R(true,busService.insertBus(bus));
    }
    //根据id更新bus信息
    @PostMapping("/update")
    public R updateBusInfo(@RequestBody Bus bus){
        busService.updateBus(bus);
        return new R(true);
    }
    @DeleteMapping("/delete/{id}")
    public R deleteBusById(@PathVariable String id){
        return new R(busService.deleteBusById(id));
    }

    @GetMapping("/page")
    public R getAllBusByPage(@RequestParam("currentPage") int currentPage,
                             @RequestParam("pageSize") int pageSize){
        return new R(true,busService.getBusByPage(currentPage,pageSize));
    }

}
