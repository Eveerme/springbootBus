package com.chen.sbbus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.sbbus.entity.Schedule;
import com.chen.sbbus.entity.Station;
import com.chen.sbbus.mapper.ScheduleMapper;
import com.chen.sbbus.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ScheduleServiceImpl extends ServiceImpl<ScheduleMapper, Schedule> implements ScheduleService {
    @Autowired
    ScheduleMapper scheduleMapper;
    @Override
    public Integer insertSchedule(Schedule schedule) {
        return scheduleMapper.insertSchedule(schedule);
    }

    @Override
    public Integer updateSchedule(Schedule schedule) {
        return scheduleMapper.updateSchedule(schedule);
    }

    @Override
    public Integer setIsDone(Integer id) {
        return scheduleMapper.setIsDone(id);
    }

    @Override
    public Schedule selectScheduleByDriverId(Integer id) {
        return scheduleMapper.selectScheduleByDriverId(id);
    }

    @Override
    public List<Schedule> getScheduleListByDriverId(Integer id) {
        return scheduleMapper.getScheduleListByDriverId(id);
    }

    @Override
    public IPage<Schedule> getScheduleByPage(Integer currentPage, Integer pageSize) {
        IPage<Schedule> page = new Page<>(currentPage,pageSize);

        QueryWrapper<Schedule> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("id");         //根据id升序排序
        return scheduleMapper.selectPage(page,queryWrapper);
    }
}
