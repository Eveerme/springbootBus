package com.chen.sbbus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.sbbus.entity.Route;

import com.chen.sbbus.mapper.RouteMapper;

import com.chen.sbbus.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteServiceImpl extends ServiceImpl<RouteMapper, Route> implements RouteService {
    @Autowired
    RouteMapper routeMapper;

    @Override
    public Integer insertRoute(Route route) {
        return routeMapper.insertRoute(route);
    }

    @Override
    public Integer updateRoute(Route route) {
        return routeMapper.updateRoute(route);
    }

    @Override
    public IPage<Route> getRouteByPage(Integer currentPage, Integer pageSize,String name) {

        IPage<Route> page = new Page<>(currentPage,pageSize);
        if (name==""){
            return routeMapper.selectPage(page,null);
        }
        QueryWrapper<Route> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("start",name);         //根据name模糊查询
        queryWrapper.like("end",name);         //根据name模糊查询
        queryWrapper.like("r1",name);         //根据name模糊查询
        queryWrapper.like("r2",name);         //根据name模糊查询
        queryWrapper.like("r3",name);         //根据name模糊查询
        queryWrapper.like("r4",name);         //根据name模糊查询
        queryWrapper.like("r5",name);         //根据name模糊查询
        queryWrapper.orderByAsc("id");         //根据id升序排序
        return routeMapper.selectPage(page,queryWrapper);
    }
}
