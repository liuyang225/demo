package com.example.demo.thread.readWriteLock;

import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
public class Test {
    public static void main(String[] args) throws Exception {
        DataContainer dataContainer = new DataContainer();
        new Thread(() -> {
            dataContainer.write();
        },"t1").start();

        Thread.sleep(100);

        new Thread(() -> {
            dataContainer.write();
        },"t2").start();
    }

}

@Slf4j
class DataContainer{
    private Object data;
    // 获取读写锁
    private ReentrantReadWriteLock rw = new ReentrantReadWriteLock();
    // 获取读锁
    private ReentrantReadWriteLock.ReadLock readLock = rw.readLock();
    // 获取写锁
    private ReentrantReadWriteLock.WriteLock writeLock = rw.writeLock();

    public Object read(){
        log.info("获取读锁");
        readLock.lock();
        try {
            log.info("读取");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return data;
        } finally {
            log.info("释放读锁");
            readLock.unlock();
        }
    }

    public void write() {
        log.info("获取写锁");
        writeLock.lock();
        try {
            log.info("写入");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }finally {
            log.info("释放写锁");
            writeLock.unlock();
        }
    }

}
