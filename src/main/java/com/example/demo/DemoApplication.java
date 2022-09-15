package com.example.demo;

import com.example.demo.spring.TestApplicationContextInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(DemoApplication.class);
        // 往初始化方法中添加一个自定义系统初始化器
        springApplication.addInitializers(new TestApplicationContextInitializer());
        SpringApplication.run(DemoApplication.class, args);
    }



}
