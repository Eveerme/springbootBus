package com.chen.sbbus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

//    @Autowired
//    private MessageService messageService;

    @RequestMapping(value = "/replyMessage", method = RequestMethod.POST)
    public String replyMessage(@RequestParam("message") String message) {
        // 将回复消息存储到数据库中
        //messageService.saveReplyMessage(message);
        return "回复消息成功！";
    }
}
