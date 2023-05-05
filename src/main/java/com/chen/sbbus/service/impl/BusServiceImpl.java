package com.chen.sbbus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.sbbus.entity.Bus;
import com.chen.sbbus.entity.Warn;
import com.chen.sbbus.mapper.BusMapper;
import com.chen.sbbus.mapper.WarnMapper;
import com.chen.sbbus.service.BusService;
import com.chen.sbbus.utils.MQTT.MQTTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class BusServiceImpl extends ServiceImpl<BusMapper, Bus> implements BusService {
    @Autowired
    private BusMapper busMapper;
    @Autowired
    private WarnMapper warnMapper;
    @Autowired
    private MQTTUtils mqttUtils;


    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)    //读已提交
    public List<Bus> findAllBus(){    //查询所有司机信息
        return busMapper.findAllBus();
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)    //读已提交
    public Bus getBusById(String id) {//根据id查询司机信息
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
        queryWrapper.orderByDesc("bus_id");   //根据id倒序排序
        return busMapper.selectPage(page,queryWrapper);
    }

    @Override
    public Boolean deleteBusById(String id) {//根据id删除司机信息
        return busMapper.deleteBusById(id);
    }

    @Override
    public Integer updateBusId(String nId, String bId) {
        return busMapper.updateBusId(nId,bId);
    }

    @Override
    public Integer warnById(String id) {//检测温度，湿度，空气质量是否合格
        Bus bus = busMapper.getBusById(id);
        String temp = bus.getBusTemp();
        String hum = bus.getBusHum();
        String air = bus.getBusAir();
        Warn warn = new Warn();
        Boolean flag = false;
        StringBuilder result = new StringBuilder();
        if(Integer.parseInt(temp)>=25){
            result=result.append("温度过高");
            flag =true;
        }
        if(Integer.parseInt(temp)<=0){
            result=result.append("温度过高低");
            flag =true;
        }
        if(Integer.parseInt(hum)>=80){
            result=result.append("湿度过高");
            flag =true;
        }
        if(Integer.parseInt(air)>=150){
            result=result.append("空气pwm过高");
            flag =true;
        }
        if(flag){
            warn.setMsg(String.valueOf(result));
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(System.currentTimeMillis());
            warn.setTime(formatter.format(date));
            warnMapper.insertWarn(warn);
            return 1;
        }
        return 0;
    }

    /*
    * 发送2为报警
    * 发送3为取消报警
    * */
    @Override
    public void alarmMsg(Integer busId, String msg) {
        String topic = "/bus/"+busId+"/sub_topic";
        mqttUtils.publishMsg(topic,msg,2);
    }

}
