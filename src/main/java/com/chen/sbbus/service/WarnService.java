package com.chen.sbbus.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.sbbus.entity.Schedule;
import com.chen.sbbus.entity.Warn;

public interface WarnService extends IService<Warn> {
    Integer insertWarn(Warn warn);
    IPage<Warn> getWarnByPage(Integer currentPage, Integer pageSize);//分页查询

    Integer updateWarnIsSolve(Warn warn);
}
