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
    @TableId(value="id",type= IdType.AUTO)//自增id
    Integer id;
    private String name;
    private String longitude_h;//经度最高
    private String longitude_l;//经度最低
    private String latitude_h;  //纬度最高
    private String latitude_l;//纬度最低

}
