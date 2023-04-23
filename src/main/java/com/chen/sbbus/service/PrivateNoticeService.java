package com.chen.sbbus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.sbbus.entity.PrivateNotice;

import java.util.List;

public interface PrivateNoticeService extends IService<PrivateNotice> {
    List<PrivateNotice> getPrivateNoticeByDriverId(Integer id);
}
