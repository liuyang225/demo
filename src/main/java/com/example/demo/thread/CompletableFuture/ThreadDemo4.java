package com.example.demo.thread.CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CompletableFuture线程接力处理示例
 */
public class ThreadDemo4 {

    public static void main(String[] args) {
        // 1. 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // 2. 提交任务，并调用join()阻塞等待任务执行完成
        String result2 = CompletableFuture.supplyAsync(() -> {
            // 睡眠一秒，模仿处理过程
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
            }
            return "结果1";
        }, executorService).thenApplyAsync(result1 -> {
            // 睡眠一秒，模仿处理过程
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
            }
            return result1 + "结果2";
        }, executorService).join();

        executorService.shutdown();
        // 3. 获取结果
        System.out.println(result2);
    }
}
