package com.example.demo.thread.Atomic;

import java.util.concurrent.atomic.*;

/**
 * 原子整数类型
 */
public class Test1 {
    public static void main(String[] args) {
        AtomicInteger a = new AtomicInteger(5);
//        int i = a.updateAndGet(x -> x + 3);
        int m = a.getAndUpdate(x -> x + 3);
        System.out.println(m);
    }
}
