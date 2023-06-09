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
@TableName("db_schedule")
public class Schedule {
    @TableId(value="id",type= IdType.AUTO)//自增id
    private Integer id;
    private String busId;
    private Integer driverId;
    private Integer routeId;
    private Integer startTime;
    private Integer dTime;
    private Integer direction;
    private Integer isDone;
    private String nextStationId;
    private Integer flag;
    private Integer isPermit;
}


