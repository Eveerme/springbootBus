package com.chen.sbbus.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.sbbus.entity.Driver;
import com.chen.sbbus.entity.PublicNotice;

import java.util.List;

public interface PublicNoticeService extends IService<PublicNotice> {
    List<PublicNotice> getAllPublicNotice();
    IPage<PublicNotice> getPublicNoticeByPage(int currentPage, int pageSize, String title);
    Integer insertPublicNotice(PublicNotice publicNotice);
    Integer updatePublicNotice(PublicNotice publicNotice);
}
