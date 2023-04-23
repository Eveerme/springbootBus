package com.chen.sbbus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.sbbus.entity.Routes;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestBody;

@Mapper
public interface RouteMapper extends BaseMapper<Routes> {
    @Insert("INSERT INTO db_routes(id,start,end,stations) VALUES(#{id},#{start},#{end},#{stations})")
    Integer insertRoute(Routes route);//插入一条新的站点信息

    @Update("update db_routes set id=#{nId},start=#{start}, end=#{end}, stations=#{stations} where id=#{bId}")
    Integer updateRoute(@RequestBody Routes route);//修改站点信息

    @Select("select * from db_routes where id = #{id}")
    Routes getRoutesById(@Param("id")Integer id);


}
