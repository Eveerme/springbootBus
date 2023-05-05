package com.chen.sbbus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.chen.sbbus.entity.Routes;
import com.chen.sbbus.entity.Station;
import com.chen.sbbus.mapper.RouteMapper;

import com.chen.sbbus.mapper.StationMapper;
import com.chen.sbbus.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RouteServiceImpl extends ServiceImpl<RouteMapper, Routes> implements RouteService {
    @Autowired
    RouteMapper routeMapper;
    @Autowired
    StationMapper stationMapper;

    @Override
    public Integer insertRoute(Routes route) {
        return routeMapper.insertRoute(route);
    }

    @Override
    public Integer updateRoute(Routes route) {
        return routeMapper.updateRoute(route);
    }

    @Override
    public Routes getRoutesById(Integer id) {
        Routes routes = routeMapper.getRoutesById(id);
        routes.setStationsList(convertStringToList(routes.getStations()));
        return routes;
    }

    @Override
    public IPage<Routes> getRoutesByPage(Integer currentPage, Integer pageSize,String name) {

        IPage<Routes> page = new Page<>(currentPage,pageSize);
        if (name.equals("")){
            return routeMapper.selectPage(page,null);
        }
        QueryWrapper<Routes> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("stations",name);         //根据name模糊查询
        queryWrapper.orderByAsc("id");         //根据id升序排序
        IPage<Routes> routesIPage = routeMapper.selectPage(page, queryWrapper);
        List<Routes> routesList = routesIPage.getRecords();

        for (Routes routes:routesList){
            String stations = routes.getStations();
            List<String> stationsId = convertStringToList(stations);
            routes.setStationsList(stationsId);
            List<String> stationsList = routes.getStationsList();
            List<String> stationNameL = new ArrayList<>();
            for (String station:stationsList) {
                stationNameL.add(stationMapper.getStationById(station).getName());
            }
            routes.setStationsNamesList(stationNameL);
        }
        return routesIPage.setRecords(routesList);
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
