package com.chen.sbbus.utils.Chat;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
public class MsgData {
    private Content content;    //消息内容
    private int isRead;         //是否已读
    private int isOneself;      //是否自己发送的消息 0否1是
    private Integer msgId;      //消息Id
    private String nickName;    //用户昵称
    private String userCode;    //用户编码
}
