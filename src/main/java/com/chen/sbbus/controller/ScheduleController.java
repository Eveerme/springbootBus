package com.chen.sbbus.controller;

import com.chen.sbbus.entity.History;
import com.chen.sbbus.entity.Routes;
import com.chen.sbbus.entity.Schedule;
import com.chen.sbbus.entity.Warn;
import com.chen.sbbus.service.HistoryService;
import com.chen.sbbus.service.RouteService;
import com.chen.sbbus.service.ScheduleService;
import com.chen.sbbus.service.WarnService;
import com.chen.sbbus.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private RouteService routeService;
    @Autowired
    private WarnService warnService;
    @Autowired
    private HistoryService historyService;

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
    public R deleteScheduleById(@PathVariable("id") Integer id){
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

    @GetMapping("/test/{did}")
    public R testSchedule(@PathVariable("did") Integer did){
        //获取系统时间
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date));
        GregorianCalendar calendar = new GregorianCalendar();
        //int nTime = calendar.get(Calendar.HOUR_OF_DAY);
        int nTime = 8;
        List<Schedule> scheduleList = scheduleService.getScheduleListByDriverId(did);
        Schedule sc = new Schedule();
        for (Schedule schedule:scheduleList){
            int sTime = schedule.getStartTime();
            int dTime = schedule.getDTime();
            if (sTime <= nTime){
                //登录时间比发车时间晚
                if (sTime + dTime > nTime){
                    //在发车时间内，正常发车
                    //返回司机调度
                    sc = schedule;
                    History history = new History();
                    history.setScheduleId(schedule.getId());
                    history.setSTime(formatter.format(date));
                    history.setCTime(formatter.format(date));
                    historyService.insertHistory(history);
                    scheduleService.setIsDone(schedule.getId());
                    break;

                }else {
                    //不在发车时间内
                    //当前找到的该司机最早的调度记录已超时,此时系统判断下一条调度表的记录
                    //记录到警告表中,msg：发车时间超时，且未完成该调度
                    Warn warn = new Warn();
                    warn.setScheduleId(schedule.getId());
                    warn.setMsg("发车时间超时，且未完成该调度");
                    warnService.insertWarn(warn);
                    scheduleService.setIsDone(schedule.getId());
                    //插入到历史记录表中
                    History history = new History();
                    history.setScheduleId(schedule.getId());
                    history.setCTime(formatter.format(date));
                    historyService.insertHistoryAll(history);
                }

            }else {
                //登录时间比发车时间早
                //返回司机调度
                sc = schedule;
                History history = new History();
                history.setScheduleId(schedule.getId());
                history.setSTime(formatter.format(date));
                history.setCTime(formatter.format(date));
                historyService.insertHistory(history);
                //记录到警告表中,msg：发车时间过早，时间
                Warn warn = new Warn();
                warn.setScheduleId(schedule.getId());
                warn.setMsg("发车时间过早,时间：" + formatter.format(date));
                warnService.insertWarn(warn);
                break;
            }

        }
        return new R(true,sc);
    }

}
