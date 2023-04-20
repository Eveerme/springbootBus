package com.chen.sbbus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.sbbus.entity.History;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface HistoryMapper extends BaseMapper<History> {

    @Insert("insert into db_history(schedule_id, s_time, is_finish) VALUES (#{scheduleId},#{sTime},0)")
    Integer insertHistory(History history); //bus启动后新增一条记录，默认未完成

    @Insert("insert into db_history(schedule_id, is_finish) VALUES (#{scheduleId},0)")
    Integer insertHistoryAll(History history); //新增一条已完成的记录

    @Update("update db_history set e_time = #{eTime} and is_finish = 1 where id = #{id}")
    Integer updateHistoryById(@Param("id") String id, @Param("eTime") String eTime);
}
