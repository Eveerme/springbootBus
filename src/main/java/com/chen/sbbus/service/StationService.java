package com.chen.sbbus.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.sbbus.entity.Bus;
import com.chen.sbbus.entity.Station;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

public interface StationService extends IService<Station> {

    Integer insertStation(Station station);      //插入一条新的站点信息
    Integer updateStation(Station station);     //修改站点信息
    Boolean deleteStationById(String id);//根据id删除站点数据

    Station getStationById(String id);

    IPage<Station> getStationByPage(Integer currentPage, Integer pageSize, String name);//分页查询

}
