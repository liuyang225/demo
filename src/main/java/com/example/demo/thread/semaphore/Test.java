package com.example.demo.thread.semaphore;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;

@Slf4j
public class Test {
    public static void main(String[] args) {
        // 可以限制线程运行的个数,3表示同时只允许3个线程运行
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    log.debug("run");
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.debug("end");
                }finally {
                    semaphore.release();
                }

            }).start();
        }
    }
}
