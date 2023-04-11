package com.chen.sbbus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.sbbus.entity.Bus;
import com.chen.sbbus.entity.Station;
import org.apache.ibatis.annotations.*;

@Mapper
public interface StationMapper extends BaseMapper<Station> {

    @Insert("INSERT INTO db_station(name,longitude_h,longitude_l,latitude_h,latitude_l) VALUES(#{name},#{longitude_h},#{longitude_l},#{latitude_h},#{latitude_l}")
    Integer insertStation(Station station);//插入一条新的站点信息

    @Update("update db_station set name=#{name}, longitude_h=#{longitude_h}, longitude_l=#{longitude_l}, latitude_h=#{latitude_h}, latitude_l=#{latitude_l} where id=#{id}")
    Integer updateStation(Station station);//修改站点信息

    @Delete("delete from db_station where id =#{id}")
    Boolean deleteStationById(@Param("id") String id);//根据id删除站点数据


}
