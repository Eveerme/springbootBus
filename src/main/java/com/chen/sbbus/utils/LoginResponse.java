package com.chen.sbbus.utils;

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
}
