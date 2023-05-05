package com.chen.sbbus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.sbbus.entity.PrivateNotice;
import com.chen.sbbus.entity.PublicNotice;
import com.chen.sbbus.mapper.PrivateNoticeMapper;
import com.chen.sbbus.service.PrivateNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class PrivateNoticeServiceImpl extends ServiceImpl<PrivateNoticeMapper, PrivateNotice> implements PrivateNoticeService {
    @Autowired
    private PrivateNoticeMapper privateNoticeMapper;
    @Override
    public List<PrivateNotice> getPrivateNoticeByDriverId(Integer id) {
        return privateNoticeMapper.getPrivateNoticeByDriverId(id);
    }
    @Override
    public IPage<PrivateNotice> getPrivateNoticeByPage(int currentPage, int pageSize, String title) {
        IPage<PrivateNotice> page = new Page<>(currentPage,pageSize);
        if (title==""){
            return privateNoticeMapper.selectPage(page,null);
        }
        QueryWrapper<PrivateNotice> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("title",title);         //根据title模糊查询
        queryWrapper.orderByAsc("time");         //根据time升序排序
        return privateNoticeMapper.selectPage(page,queryWrapper);
    }

    @Override
    public Integer insertPrivateNotice(PrivateNotice privateNotice) {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        privateNotice.setTime(formatter.format(date));
        return privateNoticeMapper.insertPrivateNotice(privateNotice);
    }

    @Override
    public Integer updatePrivateNotice(PrivateNotice privateNotice) {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        privateNotice.setTime(formatter.format(date));
        return privateNoticeMapper.updatePrivateNotice(privateNotice);
    }
}
