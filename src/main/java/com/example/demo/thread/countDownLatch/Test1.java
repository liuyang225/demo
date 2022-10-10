package com.example.demo.thread.countDownLatch;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池使用CountDownLatch
 */
@Slf4j
public class Test1 {
    public static void main(String[] args) throws Exception {
        CountDownLatch latch = new CountDownLatch(3);
        ExecutorService service = Executors.newFixedThreadPool(4);
        service.submit(() -> {
            log.info("start");
            try {
                Thread.sleep(1000);
                latch.countDown();
                log.info("end...{}", latch.getCount());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        service.submit(() -> {
            log.info("start");
            try {
                Thread.sleep(1500);
                latch.countDown();
                log.info("end...{}", latch.getCount());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        service.submit(() -> {
            log.info("start");
            try {
                Thread.sleep(1000);
                latch.countDown();
                log.info("end...{}", latch.getCount());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        service.submit(() -> {
            log.info("waiting");
            try {
                latch.await();
                log.info("wait end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
