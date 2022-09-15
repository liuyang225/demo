package com.example.demo.thread.Volatile;

/**
 * 单例模式-懒汉式升级版
 */
public final class Singleton2 {

    private Singleton2() {

    }

    /**
     * 静态内部类，只有当调用getInstance方法时才被加载，因为是在类加载的时候创建，所以是线程安全的
     */
    private static class LazyHoder {
        static final Singleton2 instance = new Singleton2();
    }

    public static Singleton2 getInstance() {
        return LazyHoder.instance;
    }
}
