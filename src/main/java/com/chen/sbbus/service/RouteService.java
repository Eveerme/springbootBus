package com.chen.sbbus.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.sbbus.entity.Routes;

import java.util.List;

public interface RouteService extends IService<Routes> {
    Integer insertRoute(Routes route);      //插入一条新的站点信息
    Integer updateRoute(Routes route);     //修改站点信息

    Routes getRoutesById(Integer id);

    IPage<Routes> getRoutesByPage(Integer currentPage, Integer pageSize, String name);//分页查询

    List<String> convertStringToList(String input);

}
