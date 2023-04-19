package com.chen.sbbus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.sbbus.entity.Manage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ManageMapper extends BaseMapper<Manage> {
    @Select("select * from db_manage where account = #{account} and password = #{password}")
    Manage getManagerByAP(@Param("account") String account,@Param("password") String password);

}
