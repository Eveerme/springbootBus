package com.chen.sbbus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.sbbus.entity.Driver;
import com.chen.sbbus.mapper.BusMapper;
import com.chen.sbbus.mapper.DriverMapper;
import com.chen.sbbus.service.DriverService;
import com.chen.sbbus.utils.DriverInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public DriverInfo getDriverInfoById(Integer id) {
        return driverMapper.getDriverById(id);
    }

    @Override
    public DriverInfo getDriverInfoByAccount(String account) {
        return driverMapper.getDriverByAccount(account);
    }

}
