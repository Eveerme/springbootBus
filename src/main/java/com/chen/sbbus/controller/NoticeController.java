package com.chen.sbbus.controller;

import com.chen.sbbus.service.PrivateNoticeService;
import com.chen.sbbus.service.PublicNoticeService;
import com.chen.sbbus.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    private PrivateNoticeService privateNoticeService;
    @Autowired
    private PublicNoticeService publicNoticeService;

    @GetMapping
    public R getAllPublicNotice(){
        return new R(true,publicNoticeService.getAllPublicNotice());
    }

    @GetMapping("/{id}")
    public R getAllPrivateNoticeById(@PathVariable("id") Integer id){
        return new R(true,privateNoticeService.getPrivateNoticeByDriverId(id));
    }


}
