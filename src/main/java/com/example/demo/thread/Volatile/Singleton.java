package com.example.demo.thread.Volatile;

/**
 * 单例模式-懒汉式
 */
public class Singleton {

    private Singleton() {

    }

    // volatile写屏障防止指令重排序
    private volatile static Singleton singleton = null;

    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
