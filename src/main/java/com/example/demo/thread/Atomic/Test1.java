package com.example.demo.thread.Atomic;

import java.util.concurrent.atomic.*;

/**
 * 原子整数类型
 */
public class Test1 {
    public static void main(String[] args) {
        AtomicInteger a = new AtomicInteger(5);
        int m = a.updateAndGet(x -> x + 3); // 返回更新后的值
        int n = a.getAndUpdate(x -> x + 3); // 返回更新前的值
        System.out.println(m);
        System.out.println(n);
    }
}
