package com.chen.sbbus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.sbbus.entity.Bus;
import com.chen.sbbus.entity.Manage;

public interface ManageService extends IService<Manage> {
    Boolean login(String account,String password);
}
