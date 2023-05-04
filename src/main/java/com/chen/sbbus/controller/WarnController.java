package com.chen.sbbus.controller;

import com.chen.sbbus.entity.Warn;
import com.chen.sbbus.service.WarnService;
import com.chen.sbbus.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

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

    @GetMapping("/beep/{sId}")
    public R insertBeepWarn(@PathVariable("sId") Integer sId){
        Warn warn = new Warn();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        warn.setTime(formatter.format(date));
        warn.setScheduleId(sId);
        warn.setMsg("公交车倾斜角度过大！！");
        warnService.insertWarn(warn);
        return new R(true);
    }
}
