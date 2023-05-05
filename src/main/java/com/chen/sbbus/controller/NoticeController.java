package com.chen.sbbus.controller;

import com.chen.sbbus.entity.PrivateNotice;
import com.chen.sbbus.entity.PublicNotice;
import com.chen.sbbus.service.PrivateNoticeService;
import com.chen.sbbus.service.PublicNoticeService;
import com.chen.sbbus.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/publicPage")
    public R getAllPublicNoticeByPage(@RequestParam("currentPage")int currentPage,
                                @RequestParam("pageSize")int pageSize,
                                @RequestParam("title")String title){
        return new R(true,publicNoticeService.getPublicNoticeByPage(currentPage, pageSize,title));
    }
    @GetMapping("/publicDelete")
    public R deletePublicNoticeById(@RequestParam("id") Integer id){
        return new R(publicNoticeService.removeById(id));
    }
    @PostMapping("/publicInsert")
    public R InsertPublicNotice(@RequestBody PublicNotice publicNotice){
        return new R(publicNoticeService.insertPublicNotice(publicNotice));
    }

    @PostMapping("/publicUpdate")
    public R updatePublicNotice(@RequestBody PublicNotice publicNotice){
        return new R(publicNoticeService.updatePublicNotice(publicNotice));
    }

    @GetMapping("/privatePage")
    public R getAllPrivateNoticeByPage(@RequestParam("currentPage")int currentPage,
                                @RequestParam("pageSize")int pageSize,
                                @RequestParam("title")String title){
        return new R(true,privateNoticeService.getPrivateNoticeByPage(currentPage, pageSize,title));
    }
    @GetMapping("/privateDelete")
    public R deletePrivateNoticeById(@RequestParam("id") Integer id){
        return new R(privateNoticeService.removeById(id));
    }
    @PostMapping("/privateInsert")
    public R InsertPrivateNotice(@RequestBody PrivateNotice privateNotice){
        return new R(privateNoticeService.insertPrivateNotice(privateNotice));
    }

    @PostMapping("/privateUpdate")
    public R updatePrivateNotice(@RequestBody PrivateNotice privateNotice){
        return new R(privateNoticeService.updatePrivateNotice(privateNotice));
    }

}
