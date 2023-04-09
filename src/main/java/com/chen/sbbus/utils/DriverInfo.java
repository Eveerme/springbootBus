package com.chen.sbbus.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverInfo {
    private Integer id;
    private String account;
    private String name;
    private String sex;
    private String phone;
    private String address;
    private Date createTime;
    private String busId;
}
