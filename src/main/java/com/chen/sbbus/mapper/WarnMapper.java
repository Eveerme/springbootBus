package com.chen.sbbus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.sbbus.entity.Warn;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface WarnMapper extends BaseMapper<Warn> {
    @Insert("insert into db_warn(schedule_id, msg, is_solve, time) values (#{scheduleId},#{msg},0,#{time})")
    Integer insertWarn(Warn warn);//插入新的警报信息，默认为未解决
    @Update("update db_warn set is_solve=#{isSolve} where id=#{id}")
    Integer updateWarnIsSolve(Warn warn);//修改isSolve
}
