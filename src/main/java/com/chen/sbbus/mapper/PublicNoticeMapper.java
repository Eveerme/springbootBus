package com.chen.sbbus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.sbbus.entity.PublicNotice;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface PublicNoticeMapper extends BaseMapper<PublicNotice> {
    @Select("select * from db_public_notice order by id desc ")
    List<PublicNotice> getAllPublicNotice();
    @Insert("insert into db_public_notice (id,title,content,time) values(#{id},#{title},#{content},#{time})")
    Integer insertPublicNotice(PublicNotice publicNotice);
    @Update("update db_public_notice set title=#{title},content=#{content},time=#{time} where id=#{id}")
    Integer updatePublicNotice(PublicNotice publicNotice);

}
