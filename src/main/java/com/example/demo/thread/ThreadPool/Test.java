package com.example.demo.thread.ThreadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Test {

    /**
     * 线程池信息： 核心线程数量5，最大数量10，队列大小20，超出核心线程数量的线程存活时间：30秒， 指定拒绝策略的
     */
    private static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 30, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(20), new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            log.error("有任务被拒绝执行了");
        }
    });

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            MyTask myTask = new MyTask(i);
            threadPoolExecutor.execute(myTask);
            System.out.println("线程池中线程数目：" + threadPoolExecutor.getPoolSize() + "，队列中等待执行的任务数目：" +
                    threadPoolExecutor.getQueue().size() + "，已执行完别的任务数目：" + threadPoolExecutor.getCompletedTaskCount());
        }
        threadPoolExecutor.shutdown();
    }

}
