package com.chen.sbbus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.sbbus.entity.Bus;
import com.chen.sbbus.mapper.BusMapper;
import com.chen.sbbus.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BusServiceImpl extends ServiceImpl<BusMapper, Bus> implements BusService {
    @Autowired
    private BusMapper busMapper;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)    //读已提交
    public List<Bus> findAllBus(){    //查询所有司机信息
        return busMapper.findAllBus();
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)    //读已提交
    public Bus getBusById(Integer id) {//根据id查询司机信息
        return busMapper.getBusById(id);
    }

    @Override
    public Integer insertBus(Bus bus){//插入一条数据
        return busMapper.insertBus(bus);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)    //读已提交
    public Integer updateBus(Bus bus){//修改数据
        return busMapper.updateBus(bus);
    }

    @Override
    public IPage<Bus> getBusByPage(Integer currentPage, Integer pageSize){//分页查询
        IPage<Bus> page = new Page<>(currentPage,pageSize);
        QueryWrapper<Bus> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");   //根据id倒序排序
        return busMapper.selectPage(page,queryWrapper);
    }

    @Override
    public Boolean deleteBusById(String id) {//根据id删除司机信息
        return busMapper.deleteBusById(id);
    }
}
