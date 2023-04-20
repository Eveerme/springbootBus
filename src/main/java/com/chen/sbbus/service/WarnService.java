package com.chen.sbbus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.sbbus.entity.Warn;

public interface WarnService extends IService<Warn> {
    Integer insertWarn(Warn warn);
}
