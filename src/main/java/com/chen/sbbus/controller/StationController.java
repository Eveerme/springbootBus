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
    @GetMapping
    public R getAllStation(){
        return new R(true,stationService.list());
    }
    @PostMapping("/insert")
    public R insertStation(@RequestParam Station station){
        return new R(true,stationService.insertStation(station));
    }
    @PostMapping("/update")
    public R updateStation(@RequestParam Station station){
        return new R(true,stationService.updateStation(station));
    }
    @GetMapping("/delete")
    public R deleteStationById(@RequestParam("id") String id){
        return new R(stationService.deleteStationById(id));
    }
    @GetMapping("/get/{id}")
    public R getStationById(@PathVariable String id){
        Station station = stationService.getStationById(id);
        return new R(true,station);
    }


    @GetMapping("/page")
    public R getAllSiteByPage(@RequestParam("currentPage")int currentPage,
                              @RequestParam("pageSize")int pageSize,
                              @RequestParam(value = "name",defaultValue = "") String name){
        return new R(true,stationService.getStationByPage(currentPage, pageSize,name));
    }
}
