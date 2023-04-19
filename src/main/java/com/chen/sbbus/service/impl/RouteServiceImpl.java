package com.chen.sbbus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.chen.sbbus.entity.Routes;
import com.chen.sbbus.mapper.RouteMapper;

import com.chen.sbbus.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RouteServiceImpl extends ServiceImpl<RouteMapper, Routes> implements RouteService {
    @Autowired
    RouteMapper routeMapper;

    @Override
    public Integer insertRoute(Routes route) {
//        return routeMapper.insertRoute(route);
        return 0;
    }

    @Override
    public Integer updateRoute(Routes route) {
//        return routeMapper.updateRoute(route);
        return 0;
    }

    @Override
    public Routes getRoutesById(Integer id) {
        Routes routes = routeMapper.getRoutesById(id);
        routes.setStationsList(convertStringToList(routes.getStations()));
        return routes;
    }

    @Override
    public IPage<Routes> getRouteByPage(Integer currentPage, Integer pageSize,String name) {

        IPage<Routes> page = new Page<>(currentPage,pageSize);
//        if (name==""){
//            return routeMapper.selectPage(page,null);
//        }
        QueryWrapper<Routes> queryWrapper = new QueryWrapper<>();
//        queryWrapper.like("start",name);         //根据name模糊查询
//        queryWrapper.like("end",name);         //根据name模糊查询
//        queryWrapper.like("r1",name);         //根据name模糊查询
//        queryWrapper.like("r2",name);         //根据name模糊查询
//        queryWrapper.like("r3",name);         //根据name模糊查询
//        queryWrapper.like("r4",name);         //根据name模糊查询
//        queryWrapper.like("r5",name);         //根据name模糊查询
//        queryWrapper.orderByAsc("id");         //根据id升序排序
        return routeMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<String> convertStringToList(String input) {
        String[] splitStrings = input.split(",");
        List<String> result = new ArrayList<>();
        for (String str : splitStrings) {
            result.add(str.trim());
        }
        return result;
    }
}
