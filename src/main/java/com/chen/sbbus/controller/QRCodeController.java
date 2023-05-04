package com.chen.sbbus.controller;

import cn.hutool.core.lang.UUID;
import com.chen.sbbus.service.QRCodeService;
import com.chen.sbbus.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * 二维码生成类
 */
@RestController

public class QRCodeController {
    @Autowired
    private QRCodeService qrCodeService;

    @GetMapping("/createQrCode/{busId}")
    public R createQrCode(@PathVariable("busId") String busId) {
        String content = "/driver/signIn/"+busId;
        String fileName = UUID.randomUUID()+".jpg";
        String RootPath = System.getProperty("user.dir")+"/src/main/resources/static/QrImgs/";
        qrCodeService.createCodeToFile(content,RootPath+ fileName);
        return new R(true);
    }
}
