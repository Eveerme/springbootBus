package com.chen.sbbus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.sbbus.entity.Route;
import com.chen.sbbus.entity.Station;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface RouteMapper extends BaseMapper<Route> {
    @Insert("INSERT INTO db_route(start,end,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10) VALUES(#{start},#{end},#{r1},#{r2},#{r3},#{r4},#{r5},#{r6},#{r7},#{r8},#{r9},#{r10})")
    Integer insertRoute(Route route);//插入一条新的站点信息

    @Update("update db_route set start=#{start}, end=#{end}, r1=#{1}, r2=#{r2}, r3=#{r3} , r4=#{r4}, r5=#{r5}, r6=#{r6}, r7=#{r7}, r8=#{r8}, r9=#{r9}, r10=#{r10} where id=#{id}")
    Integer updateRoute(Route route);//修改站点信息

}
