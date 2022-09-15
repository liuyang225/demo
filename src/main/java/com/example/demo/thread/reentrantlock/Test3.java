package com.example.demo.thread.reentrantlock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock等待超时
 */
@Slf4j
public class Test3 {
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                log.info("尝试获取锁");
                if (!lock.tryLock(2, TimeUnit.SECONDS)) {
                    log.info("没有获取到锁");
                    return;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            } finally {
                lock.unlock();
                log.info("获取到锁");
            }

        }, "t1");
        lock.lock();
        log.info("main获取到锁");
        t1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.unlock();
        log.info("main释放锁");
    }


}
