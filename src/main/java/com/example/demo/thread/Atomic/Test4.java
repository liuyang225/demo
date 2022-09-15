package com.example.demo.thread.Atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * ABA问题解决
 */
@Slf4j
public class Test4 {
    static AtomicStampedReference<String> ref = new AtomicStampedReference<>("A",0);

    public static void main(String[] args) throws Exception {
        // 拿到最新值
        String prev = ref.getReference();
        // 拿到最新版本号
        int stamp = ref.getStamp();
        log.info("main修改前值{}",prev);
        log.info("main修改前版本号{}",stamp);
        other();
        Thread.sleep(1000);
        if (ref.compareAndSet(prev, "B", stamp,stamp+1)) {
            log.info("main修改后版本号{}",stamp);
            log.info("mian修改后值{}", ref.getReference());
        }
    }

    public static void other() {
        new Thread(() -> {
            String prev = ref.getReference();
            int stamp = ref.getStamp();
            if (ref.compareAndSet(prev, "B",stamp,stamp+1)) {
                log.info("{}将{}修改成了B", Thread.currentThread().getName(), prev);
                log.info("版本号{}",stamp);
            }
        },"t1").start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            String prev = ref.getReference();
            int stamp = ref.getStamp();
            if (ref.compareAndSet(prev, "A",stamp,stamp+1)) {
                log.info("{}将{}修改成了A", Thread.currentThread().getName(), prev);
                log.info("版本号{}",stamp);
            }
        },"t2").start();
    }
}
