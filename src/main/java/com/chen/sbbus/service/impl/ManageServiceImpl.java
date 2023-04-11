package com.chen.sbbus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.chen.sbbus.entity.Manage;

import com.chen.sbbus.mapper.ManageMapper;
import com.chen.sbbus.service.DriverService;
import com.chen.sbbus.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManageServiceImpl extends ServiceImpl<ManageMapper, Manage> implements ManageService {

    @Autowired
    private ManageMapper manageMapper;

    @Override
    public Boolean login(String account, String password) {
        if(manageMapper.getManagerByAP(account,password)==null)
            return false;
        else return true;
    }

}
