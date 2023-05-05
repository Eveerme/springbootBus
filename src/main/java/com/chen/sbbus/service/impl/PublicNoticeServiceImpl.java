package com.chen.sbbus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.sbbus.entity.Driver;
import com.chen.sbbus.entity.PublicNotice;
import com.chen.sbbus.mapper.PublicNoticeMapper;
import com.chen.sbbus.service.PublicNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class PublicNoticeServiceImpl extends ServiceImpl<PublicNoticeMapper, PublicNotice> implements PublicNoticeService {
    @Autowired
    private PublicNoticeMapper publicNoticeMapper;
    @Override
    public List<PublicNotice> getAllPublicNotice() {
        return publicNoticeMapper.getAllPublicNotice();
    }

    @Override
    public IPage<PublicNotice> getPublicNoticeByPage(int currentPage, int pageSize, String title) {
        IPage<PublicNotice> page = new Page<>(currentPage,pageSize);
        if (title==""){
            return publicNoticeMapper.selectPage(page,null);
        }
        QueryWrapper<PublicNotice> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("title",title);         //根据title模糊查询
        queryWrapper.orderByAsc("time");         //根据time升序排序
        return publicNoticeMapper.selectPage(page,queryWrapper);
    }

    @Override
    public Integer insertPublicNotice(PublicNotice publicNotice) {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        publicNotice.setTime(formatter.format(date));
        return publicNoticeMapper.insertPublicNotice(publicNotice);
    }

    @Override
    public Integer updatePublicNotice(PublicNotice publicNotice) {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        publicNotice.setTime(formatter.format(date));
        return publicNoticeMapper.updatePublicNotice(publicNotice);
    }
}
