package com.chen.sbbus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.sbbus.entity.Bus;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BusMapper extends BaseMapper<Bus> {
    @Select("select * from db_bus")
    List<Bus> findAllBus();
    @Select("select * from db_bus where bus_id = #{id}")
    Bus getBusById(@Param("id")Integer id);
    @Insert("INSERT INTO db_bus(bus_id,bus_temp,bus_hum,bus_air,bus_beep,bus_latitude,bus_longitude) VALUES(#{busId},#{busTemp},#{busHum},#{busAir},#{busBeep},#{busLatitude},#{busLongitude});")
    Integer insertBus(Bus bus);

    @Update("update db_bus set bus_temp=#{busTemp}, bus_hum=#{busHum}, bus_air=#{busAir}, bus_beep=#{busBeep}, bus_latitude=#{busLatitude}, bus_longitude=#{busLongitude} where bus_id=#{busId}")
    Integer updateBus(Bus bus);

}
