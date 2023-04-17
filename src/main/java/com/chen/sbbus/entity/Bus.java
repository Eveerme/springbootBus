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

    private String id;
    private String temperature;//温度
    private String humidity;//湿度
    private String longitude;//经度
    private String latitude;//纬度
    private String velocity;//速度
    private String air;//空气质量
    private String running;//运行状态
    private String pol;//是否报警
    private String route;//公交车路线
}
