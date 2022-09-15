package com.example.demo.thread.Volatile;

/**
 * 单例模式-饿汉式
 */
public final class Singleton1 {

    private Singleton1() {

    }

    // 在类加载的时候就初始化，不存在线程安全问题
    private static final Singleton1 singleton = new Singleton1();

    public static Singleton1 getInstance() {
        return singleton;
    }
}
