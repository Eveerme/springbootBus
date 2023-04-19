package com.chen.sbbus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.sbbus.entity.Driver;
import com.chen.sbbus.entity.Driver;
import com.chen.sbbus.utils.DriverInfo;
import org.apache.ibatis.annotations.*;

import java.sql.Date;

@Mapper
public interface DriverMapper extends BaseMapper<Driver> {
    @Select("select * from db_driver where account=#{account} and password=#{password}")
    Driver getDriverByAP(@Param("account") String account,@Param("password") String password);//根据账号密码查找司机
    @Select("select password from db_driver where account=#{account}")
    String getDriverPasswordByAccount(@Param("account")String account);//根据账号查找密码
    @Select("SELECT id,account,name,sex,phone,address,create_time from db_driver where id = #{id}")
    DriverInfo getDriverById(@Param("id") String id);//根据id显示所有信息
    @Select("SELECT id,account,name,sex,phone,address,create_time from db_driver where account = #{account}")
    DriverInfo getDriverByAccount(@Param("account") String account);//根据账号显示所有信息

    @Insert("INSERT INTO db_driver(account,password,name,sex,phone,address) VALUES(#{account},#{password},#{name},#{sex},#{phone},#{address})")
    Integer insertDriver(Driver driver);//插入一条新的站点信息

    @Update("update db_driver set account=#{account}, password=#{password}, name=#{name}, sex=#{sex}, phone=#{phone} , address=#{address}, create_time=#{create_time} where id=#{id}")
    Integer updateDriver(Driver driver);//修改站点信息

}
