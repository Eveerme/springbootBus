package com.chen.sbbus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.sbbus.entity.Driver;
import com.chen.sbbus.utils.DriverInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DriverMapper extends BaseMapper<Driver> {
    @Select("select * from db_driver where account=#{account} and password=#{password}")
    Driver getDriverByAP(@Param("account") String account,@Param("password") String password);
    @Select("select password from db_driver where account=#{account}")
    String getDriverPasswordByAccount(@Param("account")String account);
    @Select("SELECT id,account,name,sex,phone,address,create_time,bus_id from db_driver where id = #{id}")
    DriverInfo getDriverById(@Param("id") Integer id);
    @Select("SELECT id,account,name,sex,phone,address,create_time,bus_id from db_driver where account = #{account}")
    DriverInfo getDriverByAccount(@Param("account") String account);
}
