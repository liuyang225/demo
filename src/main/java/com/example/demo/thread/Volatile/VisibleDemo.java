package com.example.demo.thread.Volatile;

/**
 * volatile可见性
 */
public class VisibleDemo {

    private volatile static boolean flag = false;

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (!flag) {
                    i++;
                }
                System.out.println(i);
            }
        });
        thread.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;
    }
}
