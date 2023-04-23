package com.chen.sbbus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.sbbus.entity.Schedule;
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

    @Override
    public IPage<Warn> getWarnByPage(Integer currentPage, Integer pageSize) {
        IPage<Warn> page = new Page<>(currentPage,pageSize);

        QueryWrapper<Warn> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("id");         //根据id升序排序
        return warnMapper.selectPage(page,queryWrapper);
    }

    @Override
    public Integer updateWarnIsSolve(Warn warn) {
        return warnMapper.updateWarnIsSolve(warn);
    }
}
