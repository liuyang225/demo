package com.example.demo.thread.countDownLatch;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class Test {
    public static void main(String[] args) throws Exception {
        CountDownLatch latch = new CountDownLatch(3);

        new Thread(() -> {
            log.info("start");
            try {
                Thread.sleep(1000);
                latch.countDown();
                log.info("end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1").start();

        new Thread(() -> {
            log.info("start");
            try {
                Thread.sleep(1500);
                latch.countDown();
                log.info("end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t2").start();

        new Thread(() -> {
            log.info("start");
            try {
                Thread.sleep(1000);
                latch.countDown();
                log.info("end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t3").start();

        log.info("waiting...");
        latch.await();
        log.info("wait end...");
    }
}
