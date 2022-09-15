package com.example.demo.thread.reentrantlock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock可打断
 */
@Slf4j
public class Test2 {
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                log.info("尝试获取锁");
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
                log.info("没有获取到锁");
                return;
            } finally {
                lock.unlock();
                log.info("获取到锁");
            }

        }, "t1");
        lock.lock();
        t1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("打断t1");
        t1.interrupt();

    }


}
