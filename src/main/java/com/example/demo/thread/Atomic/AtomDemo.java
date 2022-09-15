package com.example.demo.thread.Atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicInteger保证原子性
 */
public class AtomDemo {

    //初始值为0
    private static AtomicInteger a = new AtomicInteger(0);

    public static void incr() {
        try {
            //睡一会 更好看出问题
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //自增+1
        a.incrementAndGet();
    }

    public static void main(String[] args) {
        //10个线程
        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    incr();
                }
            });
            thread.start();
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a);
    }
}
