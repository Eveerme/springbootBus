package com.chen.sbbus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.sbbus.entity.History;
import org.apache.ibatis.annotations.*;

@Mapper
public interface HistoryMapper extends BaseMapper<History> {

    @Insert("insert into db_history(schedule_id, s_time, is_finish,c_time) VALUES (#{scheduleId},#{sTime},0,#{cTime})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    Integer insertHistory(History history); //bus启动后新增一条记录，默认未完成

    @Insert("insert into db_history(schedule_id, is_finish, c_time) VALUES (#{scheduleId},0,#{cTime})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    Integer insertHistoryAll(History history); //新增一条已完成的记录

    @Update("update db_history set e_time = #{eTime}, is_finish = 1 where id = #{id}")
    Integer updateHistoryById(@Param("id") Integer id, @Param("eTime") String eTime);
}
