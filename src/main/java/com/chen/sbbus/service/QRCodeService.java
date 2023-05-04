package com.chen.sbbus.service;

import javax.servlet.http.HttpServletResponse;

public interface QRCodeService {
    void createCodeToFile(String content, String filePath);
}
