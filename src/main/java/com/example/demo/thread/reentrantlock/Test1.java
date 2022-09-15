package com.example.demo.thread.reentrantlock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock可重入
 */
@Slf4j
public class Test1 {
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        m1();
    }

    public static void m1() {
        lock.lock();
        try {
           log.info("entry m1");
           m2();
        } finally {
            lock.unlock();
        }

    }

    public static void m2() {
        lock.lock();
        try {
            log.info("entry m2");
        } finally {
            lock.unlock();
        }

    }
}
