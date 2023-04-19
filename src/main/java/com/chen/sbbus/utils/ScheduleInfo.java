package com.chen.sbbus.utils;

import com.chen.sbbus.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleInfo {
    Schedule schedule;
    List<StationInfo> StationInfos;
    int stationNum;
    String start;
    String end;
}
