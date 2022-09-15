package com.example.demo.thread.AsyncManager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.TimerTask;

@Slf4j
@Component
public class Task {

    public TimerTask sleepingTest() {
        return new TimerTask() {
            @Override
            public void run() {
                // 耗时操作
                try {
                    Thread.sleep(5000);
                } catch (Exception e) {
                    log.error("SleepingTest:" + e.toString());
                }
            }
        };
    }
}
