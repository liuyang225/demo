package com.example.demo.qrcode;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/qrcode")
@Slf4j
public class TestController {

    @GetMapping("test")
    public String test() {
        QrCodeUtil.generate("http://localhost:8088/qrcode/test1", 300, 300, FileUtil.file("d:/qrcode.jpg"));
        log.info("二维码生成成功");
        return "二维码生成成功";
    }

    @GetMapping("test1")
    public String test1() {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("city", "北京");
        String result1= HttpUtil.get("https://www.baidu.com",paramMap);
        return result1;
    }
}
