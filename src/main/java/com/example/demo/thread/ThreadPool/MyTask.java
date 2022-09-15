package com.example.demo.thread.ThreadPool;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyTask implements Runnable {

    private int taskNum;

    public MyTask(int num) {
        this.taskNum = num;
    }

    @Override
    public void run() {
        log.info("正在执行task " + taskNum);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("task " + taskNum + "执行完毕");
    }
}
