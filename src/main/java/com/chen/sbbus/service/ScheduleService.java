package com.chen.sbbus.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.sbbus.entity.Schedule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScheduleService extends IService<Schedule> {
    Integer insertSchedule(Schedule schedule);      //插入一条新的站点信息
    Integer updateSchedule(Schedule schedule);     //修改站点信息

    Integer setIsDone(Integer id);//设置已检索该调度

    Schedule selectScheduleByDriverId(Integer id);

    List<Schedule> getScheduleListByDriverId(Integer id);

    IPage<Schedule> getScheduleByPage(Integer currentPage, Integer pageSize);//分页查询
    Integer getNextStationIdByBus(Integer id);
}
