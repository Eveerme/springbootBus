package com.chen.sbbus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor  //生成一个无参数的构造方法
@AllArgsConstructor //生成一个包含所有参数的构造方法

@TableName("db_route")
public class Route {
    @TableId(value="id",type= IdType.AUTO)//自增id
    private Integer id;
    private String start;
    private String end;
    private String r1;
    private String r2;
    private String r3;
    private String r4;
    private String r5;
    private String r6;
    private String r7;
    private String r8;
    private String r9;
    private String r10;
}
