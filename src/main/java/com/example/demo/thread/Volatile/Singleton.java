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
        // 第一次检查是否为null
        if (null == singleton) {
            synchronized (Singleton.class) {
                // 第二次检查是否已被其他线程获取到🔒
                if (null == singleton) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

    public static void main(String[] args) {
        new Thread(() -> {
            Singleton instance = Singleton.getInstance();
            System.out.println(instance);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1").start();

        new Thread(() -> {
            Singleton instance = Singleton.getInstance();
            System.out.println(instance);
        },"t2").start();

    }
}
