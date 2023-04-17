package com.chen.sbbus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.sbbus.entity.Bus;
import com.chen.sbbus.entity.Station;
import org.apache.ibatis.annotations.*;

@Mapper
public interface StationMapper extends BaseMapper<Station> {

    @Insert("INSERT INTO db_station(id,name,longitude,E_W,latitude,S_N) VALUES(#{id},#{name},#{longitude},#{E_W},#{latitude},#{S_N}")
    Integer insertStation(Station station);//插入一条新的站点信息

    @Update("update db_station set name=#{name}, longitude=#{longitude}, E_W=#{E_W}, latitude=#{latitude}, S_N=#{S_N} where id=#{id}")
    Integer updateStation(Station station);//修改站点信息

    @Delete("delete from db_station where id =#{id}")
    Boolean deleteStationById(@Param("id") String id);//根据id删除站点数据


}
