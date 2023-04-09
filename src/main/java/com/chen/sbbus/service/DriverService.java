package com.chen.sbbus.service;

import com.chen.sbbus.entity.Driver;
import com.chen.sbbus.utils.DriverInfo;

public interface DriverService {
    Boolean login(String name, String password);
    String getDriverPasswordByAccount(String account);
    DriverInfo getDriverInfoById(Integer id);

    DriverInfo getDriverInfoByAccount(String account);
}
