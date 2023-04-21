package com.chen.sbbus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.sbbus.entity.Schedule;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ScheduleMapper extends BaseMapper<Schedule> {
    @Insert("INSERT INTO db_schedule(bus_id,driver_id,route_id,start_time,d_time,direction,is_done,next_station_id) VALUES(#{busId},#{driverId},#{routeId},#{startTime},#{dTime},#{direction},#{isDone},#{nextStationId})")
    Integer insertSchedule(Schedule schedule);//插入一条新的站点信息
    @Update("update db_schedule set is_done = 1 where id=#{id}")
    Integer setIsDone(@Param("id") Integer id);

    @Update("update db_schedule set next_station_id=#{nextStationId} where id=#{id}")
    Integer updateSchedule(Schedule schedule);//修改站点信息
    @Update("update db_schedule set flag=#{num} where id=#{id}")
    Integer updateScheduleFlag(@Param("id")Integer id,@Param("num") Integer num);//修改站点信息
    @Select("select * from db_schedule where driver_id = #{id}")
    Schedule selectScheduleByDriverId(@Param("id") Integer id);

    @Select("select * from db_schedule where id = #{id}")
    Schedule selectScheduleById(@Param("id") Integer id);
    @Select("select * from db_schedule where driver_id = #{id} and is_done = 0 order by start_time")
    List<Schedule> getScheduleListByDriverId(@Param("id") Integer id);

}
