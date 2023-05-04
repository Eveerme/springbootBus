package com.chen.sbbus.Config;

import cn.hutool.extra.qrcode.QrConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.*;

@Configuration
public class QRConfig {

    //采用JavaConfig的方式显示注入hutool中 生成二维码
    @Bean
    public QrConfig qrConfig(){
        //初始宽度和高度
        QrConfig qrConfig=new QrConfig(500,500);

        //设置边距，即二维码和边框的距离
        qrConfig.setMargin(2);
        //设置前景色
        qrConfig.setForeColor(new Color(0xffffffff));
        //设置背景色
        qrConfig.setBackColor(new Color(0xFF000000));

        return qrConfig;
    }
}
