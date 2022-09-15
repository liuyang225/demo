package com.example.demo.thread.顺序控制;

import lombok.extern.slf4j.Slf4j;

/**
 * abcabc交替执行
 * wait/notify版本
 */
@Slf4j
public class Test3 {

    public static void main(String[] args) {
        waitnotify wn = new waitnotify(1,5);
        Thread t1 = new Thread(() -> {
            wn.print("a", 1,2);
        }, "t1");

        Thread t2 = new Thread(() -> {
            wn.print("b", 2,3);
        }, "t2");

        Thread t3 = new Thread(() -> {
            wn.print("c", 3,1);
        }, "t3");

        t1.start();
        t2.start();
        t3.start();
    }
}

class waitnotify {
    /**
     * 等待标记
     */
    private int flag;

    /**
     * 循环次数
     */
    private int loopNumber;

    public waitnotify(int flag, int loopNumber) {
        this.flag = flag;
        this.loopNumber = loopNumber;
    }

    public void print(String str, int waitFlag, int nextFlag) {
        for (int i = 0; i < loopNumber; i++) {
            synchronized (this) {
                while (flag != waitFlag) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print(str);
                flag = nextFlag;
                this.notifyAll();
            }
        }
    }
}
