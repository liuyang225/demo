package com.example.demo.qrcode;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;

public class Test {

    public static void main(String[] args) {
        // 生成指定url对应的二维码到文件，宽和高都是300像素
//        QrCodeUtil.generate("https://www.baidu.com", 300, 300, FileUtil.file("d:/qrcode.jpg"));

        String decode = QrCodeUtil.decode(FileUtil.file("d:/qrcode.jpg"));
        System.out.println(decode);
    }
}
