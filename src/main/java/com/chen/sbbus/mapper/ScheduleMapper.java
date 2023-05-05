package com.chen.sbbus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.sbbus.entity.Schedule;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ScheduleMapper extends BaseMapper<Schedule> {
    @Insert("INSERT INTO db_schedule(bus_id,driver_id,route_id,start_time,d_time,direction,is_done,next_station_id) VALUES(#{busId},#{driverId},#{routeId},#{startTime},#{dTime},#{direction},0,#{nextStationId})")
    Integer insertSchedule(Schedule schedule);//插入一条新的站点信息
    @Update("update db_schedule set is_done = 1 where id=#{id}")
    Integer setIsDone(@Param("id") Integer id);

    @Update("update db_schedule set next_station_id=#{nextStationId} where id=#{id}")
    Integer updateScheduleNSI(Schedule schedule);//修改下一个站点的信息
    @Update("update db_schedule set bus_id=#{busId},driver_id=#{driverId},route_id=#{routeId},start_time=#{startTime},d_time=#{dTime},direction=#{direction},is_done=#{isDone},next_station_id=#{nextStationId},flag=#{flag} where id=#{id}")
    Integer updateSchedule(Schedule schedule);//更新调度信息
    @Update("update db_schedule set flag=#{num} where id=#{id}")
    Integer updateScheduleFlag(@Param("id")Integer id,@Param("num") Integer num);//修改站点信息
    @Select("select * from db_schedule where driver_id = #{id}")
    Schedule selectScheduleByDriverId(@Param("id") Integer id);

    @Select("select * from db_schedule where id = #{id}")
    Schedule selectScheduleById(@Param("id") Integer id);
    @Select("select * from db_schedule where driver_id = #{id} and is_done = 0 order by start_time")
    List<Schedule> getScheduleListByDriverId(@Param("id") Integer id);
    @Select("select * from db_schedule where is_done = 0 and driver_id = #{id}")
    List<Schedule> getScheduleIsNotDone(@Param("id") Integer id);
    @Select("select * from db_schedule where is_done = 0 and driver_id = #{id} and is_permit = 1")
    List<Schedule> getScheduleIsNotDoneAndIsPermit(@Param("id")Integer id);
    @Update("update db_schedule set is_permit = #{isPermit} where id = #{id}")
    Integer setIsPermitById(@Param("id") Integer id,@Param("isPermit") Integer isPermit);
    @Select("select * from db_schedule where id = #{id}")
    Integer getIsPermitById(@Param("id") Integer id);

}
