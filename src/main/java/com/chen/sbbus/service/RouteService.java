package com.chen.sbbus.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.sbbus.entity.Route;
import com.chen.sbbus.entity.Station;
import org.apache.ibatis.annotations.Param;

public interface RouteService extends IService<Route> {
    Integer insertRoute(Route route);      //插入一条新的站点信息
    Integer updateRoute(Route route);     //修改站点信息

    IPage<Route> getRouteByPage(Integer currentPage, Integer pageSize,String name);//分页查询
}
