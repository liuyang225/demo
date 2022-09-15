package com.example.demo.thread.顺序控制;

import lombok.extern.slf4j.Slf4j;

/**
 * 先打印t1，在打印t2
 * join版本
 */
@Slf4j
public class Test2 {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            log.info("t1");
        }, "t1");

        Thread t2 = new Thread(() -> {
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("t2");
        }, "t2");

        t1.start();
        t2.start();
    }
}
