package com.example.demo.spring.aop;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/aop")
@RestController
public class TestAOPController {

    @SystemLog(type = SystemLogEnum.SAVE_LOG)
    @GetMapping("/test")
    public String test() {
        return "保存成功";
    }
}
