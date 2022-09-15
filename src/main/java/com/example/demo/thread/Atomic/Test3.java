package com.example.demo.thread.Atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

/**
 * ABA问题
 */
@Slf4j
public class Test3 {
    static AtomicReference<String> ref = new AtomicReference<>("A");

    public static void main(String[] args) throws Exception {
        String prev = ref.get();
        log.info("main修改前{}",prev);
        other();
        Thread.sleep(1000);
        if (ref.compareAndSet(prev, "B")) {
            log.info("mian修改后{}", ref.get());
        }
    }

    public static void other() {
        new Thread(() -> {
            String prev = ref.get();
            if (ref.compareAndSet(prev, "B")) {
                log.info("{}将{}修改成了B", Thread.currentThread().getName(), prev);
            }
        },"t1").start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            String prev = ref.get();
            if (ref.compareAndSet(prev, "A")) {
                log.info("{}将{}修改成了A", Thread.currentThread().getName(), prev);
            }
        },"t2").start();
    }
}
