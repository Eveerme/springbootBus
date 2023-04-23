package com.chen.sbbus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import com.chen.sbbus.MyObjectDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor  //生成一个无参数的构造方法
@AllArgsConstructor //生成一个包含所有参数的构造方法
@TableName("db_routes")

//@JsonDeserialize(using = MyObjectDeserializer.class)
public class Routes {

    private Integer id;
    private String start;
    private String end;
    @TableField("stations")
    private String stations;
    @TableField(exist = false)
    private List<String> stationsList;
    @JsonProperty("nId")
    @TableField(exist = false)
    private  Integer nId;
    @JsonProperty("bId")
    @TableField(exist = false)
    private  Integer bId;

}
