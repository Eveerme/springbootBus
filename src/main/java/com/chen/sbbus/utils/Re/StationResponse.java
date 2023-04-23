package com.chen.sbbus.utils.Re;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StationResponse {
    Integer steps;
    String nextStationName;
}
