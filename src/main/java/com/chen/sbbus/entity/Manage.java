package com.chen.sbbus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("db_manage")
public class Manage {

    private String userName;
    private String password;
    private String account;

    //以下是数据库字段
    private Long id;
    private String name;
    private Integer age;
    private String email;


}
