package com.chen.sbbus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.sbbus.entity.History;


public interface HistoryService extends IService<History> {
    Integer insertHistory(History history); //bus启动后新增一条记录，默认未完成

    Integer insertHistoryAll(History history); //新增一条已完成的记录

    Integer updateHistoryById(Integer id, String eTime);
}
