package com.chen.sbbus.utils.Re;

import com.chen.sbbus.utils.DriverInfo;
import com.chen.sbbus.utils.ScheduleInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private Boolean status;
    private String token;
    private DriverInfo driverInfo;
    private ScheduleInfo scheduleInfo;
}
