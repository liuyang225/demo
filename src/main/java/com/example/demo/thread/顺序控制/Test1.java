package com.example.demo.thread.顺序控制;

import lombok.extern.slf4j.Slf4j;

/**
 * 先打印t2，在打印t1
 * wait/notify版本
 */
@Slf4j
public class Test1 {
    static final Object lock = new Object();
    static boolean t2runned = false;

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (lock) {
                while (!t2runned) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.info("t1");
            }
        }, "t1").start();

        new Thread(() -> {
            synchronized (lock) {
                log.info("t2");
                t2runned = true;
                lock.notify();
            }
        }, "t2").start();
    }
}
