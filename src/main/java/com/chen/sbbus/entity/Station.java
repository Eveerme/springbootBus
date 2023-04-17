package com.chen.sbbus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@TableName("db_station")
public class Station {
//    @TableId(value="id",type= IdType.AUTO)//自增id
    Integer id;
    private String name;
    private float longitude;//经度
    private String E_W;//
    private float latitude;  //纬度
    private String S_N;//

}
