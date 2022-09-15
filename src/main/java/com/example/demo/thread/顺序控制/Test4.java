package com.example.demo.thread.顺序控制;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * abcabc交替执行
 * ReentrantLock版本
 */
@Slf4j
public class Test4 {

    public static void main(String[] args) throws InterruptedException {
        awaitSingal as = new awaitSingal(5);
        Condition a = as.newCondition();
        Condition b = as.newCondition();
        Condition c = as.newCondition();
        new Thread(() -> {
            as.print("a", a, b);
        }).start();
        new Thread(() -> {
            as.print("b", b, c);
        }).start();
        new Thread(() -> {
            as.print("c", c, a);
        }).start();
        Thread.sleep(1000);
        as.lock();
        try {
            a.signal();
        } finally {
           as.unlock();
        }
    }
}

class awaitSingal extends ReentrantLock {
    /**
     * 循环次数
     */
    private int loopNumber;

    public awaitSingal(int loopNumber) {
        this.loopNumber = loopNumber;
    }

    /**
     *
     * @param str 打印内容
     * @param current 当前休息室
     * @param next 下一个休息室
     */
    public void print(String str, Condition current, Condition next) {
        lock();
        try {
            for (int i = 0; i < loopNumber; i++) {
                try {
                    current.await();
                    System.out.print(str);
                    next.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            unlock();
        }
    }
}
