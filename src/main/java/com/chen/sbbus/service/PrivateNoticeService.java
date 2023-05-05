package com.chen.sbbus.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.sbbus.entity.PrivateNotice;
import com.chen.sbbus.entity.PublicNotice;

import java.util.List;

public interface PrivateNoticeService extends IService<PrivateNotice> {
    List<PrivateNotice> getPrivateNoticeByDriverId(Integer id);

    IPage<PrivateNotice> getPrivateNoticeByPage(int currentPage, int pageSize, String title);

    Integer insertPrivateNotice(PrivateNotice privateNotice);

    Integer updatePrivateNotice(PrivateNotice privateNotice);
}
