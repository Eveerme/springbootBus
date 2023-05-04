package com.chen.sbbus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.sbbus.entity.*;
import com.chen.sbbus.mapper.*;
import com.chen.sbbus.service.RouteService;
import com.chen.sbbus.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ScheduleServiceImpl extends ServiceImpl<ScheduleMapper, Schedule> implements ScheduleService {
    @Autowired
    ScheduleMapper scheduleMapper;
    @Autowired
    BusMapper busMapper;
    @Autowired
    RouteMapper routeMapper;
    @Autowired
    WarnMapper warnMapper;
    @Autowired
    StationMapper stationMapper;
    @Autowired
    RouteService routeService;


    @Override
    public Integer insertSchedule(Schedule schedule) {
        return scheduleMapper.insertSchedule(schedule);
    }

    @Override
    public Integer updateScheduleNSI(Schedule schedule) {
        return scheduleMapper.updateScheduleNSI(schedule);
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
    @Override
    public Integer getNextStationIdByBus(Integer id){//根据bus的经纬度判断公交车站点信息,更新next_station_id
        Schedule schedule = scheduleMapper.selectScheduleById(id);//根据id获取Schedule;
        //获取司机信息
        String busId = schedule.getBusId();
        Bus bus = busMapper.getBusById(busId);

        //获取路线信息
        Integer routeId = schedule.getRouteId();
        Routes routes= routeService.getRoutesById(routeId);
        List<String> stationsList = routes.getStationsList();//路线的所有站点id
        int j1=-1;

        Boolean flag=false;
        StringBuilder result = new StringBuilder();
        //获取站点信息
        for(int i=0;i<stationsList.size();i++){//判断bus在哪个站点

            Station station = stationMapper.getStationById(stationsList.get(i));//根据站点id获取station
            //根据经纬度判断
            if(bus.getBusLatitude()<=(station.getLatitude()+0.002)
                    &&bus.getBusLatitude()>=(station.getLatitude()-0.002)
                    &&bus.getBusLongitude()<=(station.getLongitude()+0.002)
                    &&bus.getBusLongitude()>=(station.getLongitude()-0.002)
                    &&bus.getBusEW().equals(station.getEw())
                    &&bus.getBusNS().equals(station.getNs())
            ){//在本站点
                if((!station.getId().equals(schedule.getNextStationId())&&schedule.getFlag()==1&&!station.getId().equals(routes.getStart()))
                        ||(station.getId().equals(routes.getStart())&&schedule.getFlag()==1&&stationsList.get(i).equals(schedule.getNextStationId()))){//判断是否出现越过其他站点等错误
                    //说明出现错误，将数据插入warn中并加入越过的站点
                    Warn warn = new Warn();
                    warn.setScheduleId(schedule.getId());
                    if(stationsList.get(i).equals(schedule.getNextStationId())){
                        warn.setMsg("下一站点信息错误，当前站点为"+station.getId());
                    }
                    else {
                        //找到schedule的下一个站点信息并确定越过的站点
                        for (int j = 0; j < stationsList.size(); j++) {
                            if(schedule.getNextStationId().equals(stationsList.get(j))){
                                j1=j;
                            }
                            if(j1>0 && j1!=i){
                                result=result.append(stationsList.get(j1)+" ");
                                j1++;
                            }

                        }
                        warn.setMsg("没有按照指定站点路线行驶，未经过的站点id为："+result);
                    }
                    warnMapper.insertWarn(warn);

                }
                scheduleMapper.updateScheduleFlag(schedule.getId(),0);//站点测试通过
                //更新schedule数据，返回经过站点数
                flag=true;
                //判断是否在终点站
                if(station.getId().equals(routes.getEnd())){
                    schedule.setNextStationId(stationsList.get(i));
                }else {
                    schedule.setNextStationId(stationsList.get(i+1));
                    scheduleMapper.updateScheduleNSI(schedule);
                }

                return i;
            }
            else{
                scheduleMapper.updateScheduleFlag(schedule.getId(),1);
            }


        }
        if(!flag){//bus不在站点
            //判断该站点是第几个站点
            for (int i = 0; i < stationsList.size(); i++) {
                if(stationsList.get(i).equals(schedule.getNextStationId())){
                    return i;
                }
            }
            return -1;
        }


        return 0;
    }

    @Override
    public List<Schedule> getScheduleIsNotDone(Integer id) {
        return scheduleMapper.getScheduleIsNotDone(id);
    }

    @Override
    public List<Schedule> getScheduleIsNotDoneAndIsPermit(Integer id) {
        return scheduleMapper.getScheduleIsNotDoneAndIsPermit(id);
    }

    @Override
    public Integer setIsPermitById(Integer id, Integer isPermit) {
        return scheduleMapper.setIsPermitById(id,isPermit);
    }

    @Override
    public Integer getIsPermitById(Integer id) {
        return scheduleMapper.getIsPermitById(id);
    }


}
