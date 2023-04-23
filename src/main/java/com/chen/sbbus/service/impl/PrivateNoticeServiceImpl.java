package com.chen.sbbus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.sbbus.entity.PrivateNotice;
import com.chen.sbbus.mapper.PrivateNoticeMapper;
import com.chen.sbbus.service.PrivateNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrivateNoticeServiceImpl extends ServiceImpl<PrivateNoticeMapper, PrivateNotice> implements PrivateNoticeService {
    @Autowired
    private PrivateNoticeMapper privateNoticeMapper;
    @Override
    public List<PrivateNotice> getPrivateNoticeByDriverId(Integer id) {
        return privateNoticeMapper.getPrivateNoticeByDriverId(id);
    }
}
