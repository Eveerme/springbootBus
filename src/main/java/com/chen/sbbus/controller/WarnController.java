package com.chen.sbbus.controller;

import com.chen.sbbus.entity.Warn;
import com.chen.sbbus.service.WarnService;
import com.chen.sbbus.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/warn")
public class WarnController {
    @Autowired
    WarnService warnService;

    @GetMapping("/page")
    public R getWarnByPage(@RequestParam("currentPage")int currentPage,
                           @RequestParam("pageSize")int pageSize){
        return new R(warnService.getWarnByPage(currentPage,pageSize));
    }
    @PostMapping("/update")
    public R updateWarnIsSolve(@RequestBody Warn warn){

        return new R(warnService.updateWarnIsSolve(warn));
    }

}
