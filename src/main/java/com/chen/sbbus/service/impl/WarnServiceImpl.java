package com.chen.sbbus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.sbbus.entity.Warn;
import com.chen.sbbus.mapper.WarnMapper;
import com.chen.sbbus.service.WarnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WarnServiceImpl extends ServiceImpl<WarnMapper, Warn> implements WarnService {

    @Autowired
    private WarnMapper warnMapper;

    @Override
    public Integer insertWarn(Warn warn) {
        return warnMapper.insertWarn(warn);
    }
}
