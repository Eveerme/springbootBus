package com.chen.sbbus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.sbbus.entity.Station;
import com.chen.sbbus.mapper.StationMapper;
import com.chen.sbbus.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StationServiceImpl extends ServiceImpl<StationMapper, Station>implements StationService {
    @Autowired
    StationMapper stationMapper;

    @Override
    public Integer insertStation(Station station) {//插入一条新的站点信息
        return stationMapper.insertStation(station);
    }

    @Override
    public Integer updateStation(Station station) {
        return stationMapper.updateStation(station);
    }

    @Override
    public Boolean deleteStationById(String id) {
        return stationMapper.deleteStationById(id);
    }

    @Override
    public IPage<Station> getStationByPage(Integer currentPage, Integer pageSize, String name) {
        IPage<Station> page = new Page<>(currentPage,pageSize);
        if (name==""){
            return stationMapper.selectPage(page,null);
        }
        QueryWrapper<Station> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name",name);         //根据name模糊查询
        queryWrapper.orderByAsc("id");         //根据id升序排序
        return stationMapper.selectPage(page,queryWrapper);
    }
}
