package com.chen.sbbus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.sbbus.entity.PublicNotice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PublicNoticeMapper extends BaseMapper<PublicNotice> {
    @Select("select * from db_public_notice order by id desc ")
    List<PublicNotice> getAllPublicNotice();
}
