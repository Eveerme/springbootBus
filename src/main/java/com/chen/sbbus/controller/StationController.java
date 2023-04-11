package com.chen.sbbus.controller;

import com.chen.sbbus.entity.Station;
import com.chen.sbbus.service.StationService;
import com.chen.sbbus.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/station")
public class StationController {

    @Autowired
    private StationService stationService;
    @PostMapping("/insert")
    public R insertStation(@RequestParam Station station){
        return new R(true,stationService.insertStation(station));
    }
    @PostMapping("/update")
    public R updateStation(@RequestParam Station station){
        stationService.updateStation(station);
        return new R(true);
    }
    @DeleteMapping("/delete/{id}")
    public R deleteStationById(@PathVariable String id){
        return new R(stationService.deleteStationById(id));
    }
    @GetMapping("/get/{id}")
    public R getStationById(@PathVariable String id){
        return new R(true,stationService.getById(id));
    }
    @GetMapping("/getAll")
    public R getAllStation(){
        return new R(true,stationService.list());
    }

    @GetMapping("/page")
    public R getAllSiteByPage(@RequestParam("currentPage")int currentPage,
                              @RequestParam("pageSize")int pageSize,
                              @RequestParam(value = "name",defaultValue = "") String name){
        return new R(true,stationService.getStationByPage(currentPage, pageSize,name));
    }
}
