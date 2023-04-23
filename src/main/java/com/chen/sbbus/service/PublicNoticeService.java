package com.chen.sbbus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.sbbus.entity.PublicNotice;

import java.util.List;

public interface PublicNoticeService extends IService<PublicNotice> {
    List<PublicNotice> getAllPublicNotice();
}
