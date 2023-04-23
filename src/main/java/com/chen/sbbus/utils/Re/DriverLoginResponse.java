package com.chen.sbbus.utils.Re;

import com.chen.sbbus.utils.DriverInfo;
import com.chen.sbbus.utils.ScheduleInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverLoginResponse {
    private Boolean status;
    private String token;
    private DriverInfo driverInfo;
    private List<ScheduleInfo> scheduleInfoList;
    private Integer scheduleNum;
}
