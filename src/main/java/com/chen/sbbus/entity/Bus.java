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
@TableName("db_bus")
public class Bus {
    @TableId(value="bus_id")
    private String busId;
    private String busTemp;
    private String busHum;
    private String busAir;
    private Boolean busBeep;
    private float busLatitude;
    private String busNS;
    private float busLongitude;
    private String busEW;
    private float busSpeed;
}
