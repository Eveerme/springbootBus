package com.chen.sbbus.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.sbbus.entity.Bus;
import com.chen.sbbus.entity.Driver;
import com.chen.sbbus.entity.Driver;
import com.chen.sbbus.utils.DriverInfo;

public interface DriverService extends IService<Driver> {
    Boolean login(String name, String password);
    String getDriverPasswordByAccount(String account);
    DriverInfo getDriverInfoById(Integer id);

    DriverInfo getDriverInfoByAccount(String account);
    Integer insertDriver(Driver driver);      //插入一条新的站点信息
    Integer updateDriver(Driver driver);     //修改站点信息

    IPage<Driver> getDriverByPage(Integer currentPage, Integer pageSize, String name);//分页查询
}
