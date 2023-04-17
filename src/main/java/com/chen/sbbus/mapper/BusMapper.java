package com.chen.sbbus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.sbbus.entity.Bus;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BusMapper extends BaseMapper<Bus> {
    @Select("select * from db_bus")
    List<Bus> findAllBus();//查找所有司机信息
    @Select("select * from db_bus where id = #{id}")
    Bus getBusById(@Param("id")String id);//根据id查找司机信息
    @Insert("INSERT INTO db_bus(id,temperature,humidity,longitude,latitude,velocity,air,running,pol,route) VALUES(#{id},#{temperature},#{humidity},#{longitude},#{latitude},#{velocity},#{air},#{running},#{pol},#{route});")
    Integer insertBus(Bus bus);//插入信息

    @Update("update db_bus set temperature=#{temperature}, humidity=#{humidity}, longitude=#{longitude}, latitude=#{latitude}, velocity=#{velocity}, air=#{air}, running=#{running}, pol=#{pol}, route=#{route} where id=#{id}")
    Integer updateBus(Bus bus);//修改信息

    @Delete("delete from db_bus where id =#{id}")
    Boolean deleteBusById(@Param("id") String id);//根据id删除数据


}
