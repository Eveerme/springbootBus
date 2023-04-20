package com.chen.sbbus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.sbbus.entity.History;
import com.chen.sbbus.mapper.HistoryMapper;
import com.chen.sbbus.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryServiceImpl extends ServiceImpl<HistoryMapper, History> implements HistoryService {
    @Autowired
    private HistoryMapper historyMapper;
    @Override
    public Integer insertHistory(History history) {
        return historyMapper.insertHistory(history);
    }

    @Override
    public Integer insertHistoryAll(History history) {
        return historyMapper.insertHistoryAll(history);
    }

    @Override
    public Integer updateHistoryById(String id, String eTime) {
        return historyMapper.updateHistoryById(id, eTime);
    }
}
