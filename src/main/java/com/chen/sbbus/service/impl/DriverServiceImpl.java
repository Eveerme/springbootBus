package com.chen.sbbus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.sbbus.entity.Driver;
import com.chen.sbbus.mapper.DriverMapper;
import com.chen.sbbus.service.DriverService;
import com.chen.sbbus.utils.DriverInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverServiceImpl extends ServiceImpl<DriverMapper,Driver> implements DriverService {
    @Autowired
    private DriverMapper driverMapper;
    @Override
    public Boolean login(String account, String password) {
        Driver driver = driverMapper.getDriverByAP(account, password);
        if (driver == null){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public String getDriverPasswordByAccount(String account) {
        return driverMapper.getDriverPasswordByAccount(account);
    }

    @Override
    public DriverInfo getDriverInfoById(String id) {
        return driverMapper.getDriverById(id);
    }

    @Override
    public Integer getDriverIsOnlineById(Integer id) {
        return driverMapper.getDriverIsOnlineById(id);
    }

    @Override
    public DriverInfo getDriverInfoByAccount(String account) {
        return driverMapper.getDriverByAccount(account);
    }

    @Override
    public Integer insertDriver(Driver driver) {
        return driverMapper.insertDriver(driver);
    }

    @Override
    public Integer updateDriver(Driver driver) {
        return driverMapper.updateDriver(driver);
    }

    @Override
    public Integer updateDriverIsOnline(Integer id, int i) {
        return driverMapper.updateDriverIsOnline(id, i);
    }

    @Override
    public List<Driver> selectDriverOnline() {
        return driverMapper.selectDriverOnline();
    }

    @Override
    public IPage<Driver> getDriverByPage(Integer currentPage, Integer pageSize, String name) {

        IPage<Driver> page = new Page<>(currentPage,pageSize);
        if (name==""){
            return driverMapper.selectPage(page,null);
        }
        QueryWrapper<Driver> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name",name);         //根据name模糊查询
        queryWrapper.orderByAsc("id");         //根据id升序排序
        return driverMapper.selectPage(page,queryWrapper);
    }

}
