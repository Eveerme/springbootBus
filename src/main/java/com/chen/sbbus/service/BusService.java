package com.chen.sbbus.service;

import com.chen.sbbus.entity.Bus;

import java.util.List;

public interface BusService {
    List<Bus> findAllBus();
    Bus getBusById(Integer id);
    Integer insertBus(Bus bus);
    Integer updateBus(Bus bus);
}
