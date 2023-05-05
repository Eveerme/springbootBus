package com.chen.sbbus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.sbbus.entity.PrivateNotice;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PrivateNoticeMapper extends BaseMapper<PrivateNotice> {
    @Select("select * from db_private_notice where driver_id = #{id} order by id desc")
    List<PrivateNotice> getPrivateNoticeByDriverId(@Param("id")Integer id);
    @Insert("insert db_private_notice (driver_id,title,content,time) values(#{driverId},#{title},#{content},#{time})")
    Integer insertPrivateNotice(PrivateNotice privateNotice);
    @Update("update db_private_notice set driver_id=#{driverId},title=#{title},content=#{content},time=#{time} where id=#{id}")
    Integer updatePrivateNotice(PrivateNotice privateNotice);
}
