package com.chen.sbbus.utils.Chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatResponse {
    private int code;   // 响应编码
    private String msg; //响应信息
    private int total;
    private String sysTime;//系统响应时间
    private List<MsgData> data;
}
