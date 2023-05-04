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
@TableName("db_warn")
public class Warn {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private Integer scheduleId;
    private String msg;
    private Integer isSolve;
    private String time;
}
