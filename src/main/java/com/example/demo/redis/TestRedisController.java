package com.example.demo.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/redis")
public class TestRedisController {

    private static final String tokenCacheKey = "demo2:redis";

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/set")
    public void set() {
        for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setId(i);
            user.setName("张三" + i);
            user.setAge(5*i);

            redisTemplate.opsForValue().set(tokenCacheKey + ":" + user.getId(), user.toString(),300, TimeUnit.SECONDS);
        }



        System.out.println("redis存入成功");
    }

    @GetMapping("/get")
    public Object get(Integer id) {
        Object o = redisTemplate.opsForValue().get(tokenCacheKey + ":" + id);
        return o;
    }
}
