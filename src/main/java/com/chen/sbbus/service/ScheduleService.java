package com.chen.sbbus.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.sbbus.entity.Route;
import com.chen.sbbus.entity.Schedule;

public interface ScheduleService extends IService<Schedule> {
    Integer insertSchedule(Schedule schedule);      //插入一条新的站点信息
    Integer updateSchedule(Schedule schedule);     //修改站点信息

    IPage<Schedule> getScheduleByPage(Integer currentPage, Integer pageSize);//分页查询
}
