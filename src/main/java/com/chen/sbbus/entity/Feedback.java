package com.chen.sbbus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("db_feedback")
public class Feedback {
    @TableId(value="id",type= IdType.AUTO)//自增id
    private Integer id;
    private Integer driverId;
    private String content;
    private String time;
    private String imgList;
    private Integer isSolve;
    private String solveTime;
    @TableField(exist = false)
    private List<String> imgLists;
}
