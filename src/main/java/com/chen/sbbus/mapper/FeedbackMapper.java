package com.chen.sbbus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.sbbus.entity.Feedback;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FeedbackMapper extends BaseMapper<Feedback> {
    @Select("select * from db_feedback")
    List<Feedback> getAllFeedbackList();
    @Select("select * from db_feedback where driver_id = #{id} order by id desc")
    List<Feedback> getFeedbackListByDriverId(@Param("id") Integer id);
    @Select("select * from db_feedback where id = #{id}")
    Feedback getFeedbackListById(@Param("id") Integer id);
    @Insert("insert into db_feedback(driver_id, content, time,img_list, is_solve) VALUES (#{driverId},#{content},#{time},#{imgList},0)")
    Integer insertFeedback(Feedback feedback);
}
