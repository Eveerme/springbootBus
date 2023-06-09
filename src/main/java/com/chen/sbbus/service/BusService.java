package com.chen.sbbus.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.sbbus.entity.Bus;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BusService extends IService<Bus> {
    List<Bus> findAllBus();
    Bus getBusById(String id);
    Integer insertBus(Bus bus);
    Integer updateBus(Bus bus);
    IPage<Bus> getBusByPage(Integer currentPage, Integer pageSize);
    Boolean deleteBusById(String id);
    Integer updateBusId(String nId, String bId);

    Integer warnById(String id);

    void alarmMsg(Integer busId,String msg);
}
