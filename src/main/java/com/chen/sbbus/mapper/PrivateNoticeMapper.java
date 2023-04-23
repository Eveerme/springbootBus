package com.chen.sbbus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.sbbus.entity.PrivateNotice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PrivateNoticeMapper extends BaseMapper<PrivateNotice> {
    @Select("select * from db_private_notice where driver_id = #{id} order by id desc")
    List<PrivateNotice> getPrivateNoticeByDriverId(@Param("id")Integer id);
}
