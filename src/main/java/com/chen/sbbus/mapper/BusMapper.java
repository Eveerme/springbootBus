package com.chen.sbbus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.sbbus.entity.Bus;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BusMapper extends BaseMapper<Bus> {
    @Select("select * from db_bus")
    List<Bus> findAllBus();//查找所有司机信息
    @Select("select * from db_bus where bus_id = #{id}")
    Bus getBusById(@Param("id")String id);//根据id查找司机信息
    @Insert("INSERT INTO db_bus(bus_id,bus_temp,bus_hum,bus_air,bus_beep,bus_latitude,bus_n_s,bus_longitude,bus_e_w) VALUES(#{busId},#{busTemp},#{busHum},#{busAir},#{busBeep},#{busLatitude},#{busNS},#{busLongitude},#{busEW});")
    Integer insertBus(Bus bus);//插入信息

    @Update("update db_bus set bus_temp=#{busTemp}, bus_hum=#{busHum}, bus_air=#{busAir}, bus_beep=#{busBeep}, bus_latitude=#{busLatitude}, bus_n_s=#{busNS}, bus_longitude=#{busLongitude}, bus_e_w=#{busEW} where bus_id=#{busId}")
    Integer updateBus(Bus bus);//修改信息

    @Delete("delete from db_bus where bus_id =#{id}")
    Boolean deleteBusById(@Param("id") String id);//根据id删除数据


}
