package com.chen.sbbus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
    private String id;
    private String name;
    private float longitude;//经度最高
    @TableField("ew")
    private String ew;//经度最低
    private float latitude;  //纬度最高
    @TableField("ns")
    private String ns;//纬度最低

}
