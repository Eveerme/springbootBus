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
@TableName("db_public_notice")
public class PublicNotice {
    @TableId(value="id",type= IdType.AUTO)//自增id
    private Integer id;
    private String title;
    private String content;
    private String time;
}
