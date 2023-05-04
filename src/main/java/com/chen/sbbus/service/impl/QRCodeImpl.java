package com.chen.sbbus.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeException;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.chen.sbbus.service.QRCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QRCodeImpl implements QRCodeService {

    @Autowired
    private QrConfig config;

    //生成到文件
    @Override
    public void createCodeToFile(String content, String filePath) {
        try {
            QrCodeUtil.generate(content,config, FileUtil.file(filePath));
        } catch (QrCodeException e) {
            e.printStackTrace();
        }
    }
}
