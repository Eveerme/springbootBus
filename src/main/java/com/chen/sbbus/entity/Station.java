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
//    @TableId(value="id",type= IdType.AUTO)//自增id
    Integer id;
    private String name;
    private float longitude;//经度最高
    @TableField("E_W")
    private String E_W;//经度最低
    private float latitude;  //纬度最高
    @TableField("S_N")
    private String S_N;//纬度最低

}
