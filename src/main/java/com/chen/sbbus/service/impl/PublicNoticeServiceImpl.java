package com.chen.sbbus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.sbbus.entity.PublicNotice;
import com.chen.sbbus.mapper.PublicNoticeMapper;
import com.chen.sbbus.service.PublicNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicNoticeServiceImpl extends ServiceImpl<PublicNoticeMapper, PublicNotice> implements PublicNoticeService {
    @Autowired
    private PublicNoticeMapper publicNoticeMapper;
    @Override
    public List<PublicNotice> getAllPublicNotice() {
        return publicNoticeMapper.getAllPublicNotice();
    }
}
