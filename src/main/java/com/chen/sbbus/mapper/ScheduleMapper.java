package com.chen.sbbus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.sbbus.entity.Schedule;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ScheduleMapper extends BaseMapper<Schedule> {
    @Insert("INSERT INTO db_schedule(bus_id,driver_id,route_id,start_time,end_time,date) VALUES(#{bus_id},#{driver_id},#{route_id},#{start_time},#{end_time},#{date}")
    Integer insertSchedule(Schedule schedule);//插入一条新的站点信息

    @Update("update db_schedule set bus_id=#{bus_id}, driver_id=#{driver_id}, route_id=#{route_id}, start_time=#{start_time}, end_time=#{end_time}, date=#{date} where id=#{id}")
    Integer updateSchedule(Schedule schedule);//修改站点信息
    @Select("select * from db_schedule where driver_id = #{id}")
    Schedule selectScheduleByDriverId(@Param("id") Integer id);

}
