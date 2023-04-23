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
    List<StationInfo> stationInfos;
    int stationNum; //站点总数量
    String start;   //起始站点名称
    String end;     //结束站点名称
    Integer historyId;//记录表的id
}
