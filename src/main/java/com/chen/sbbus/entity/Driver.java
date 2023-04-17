package com.chen.sbbus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("db_driver")
public class Driver {
   // @TableId(type = IdType.AUTO)
    private String id;
    private String account;
    private String password;
    private String name;
    private String sex;
    private String phone;
    private String address;
    private Date create_time;
    private String busId;
    private int isOnline;
}
