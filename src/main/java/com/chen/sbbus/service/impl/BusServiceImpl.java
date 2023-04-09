package com.chen.sbbus.service.impl;

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

    @Transactional(isolation = Isolation.READ_COMMITTED)    //读已提交
    public List<Bus> findAllBus(){
        return busMapper.findAllBus();
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)    //读已提交
    public Bus getBusById(Integer id) {
        return busMapper.getBusById(id);
    }

    public Integer insertBus(Bus bus){
        return busMapper.insertBus(bus);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)    //读已提交
    public Integer updateBus(Bus bus){
        return busMapper.updateBus(bus);
    }
}
