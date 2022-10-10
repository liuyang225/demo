package com.example.demo.thread.cyclicbarrier;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class Test {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> {
            log.info("start1,start2 finished");
        });
        service.submit(() -> {
            log.info("start1");
            try {
                Thread.sleep(1000);
                cyclicBarrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        service.submit(() -> {
            log.info("start2");
            try {
                Thread.sleep(2000);
                cyclicBarrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        service.shutdown();
    }
}
