package com.example.demo.thread.aqs;

import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

@Slf4j
public class TestAqs {
    public static void main(String[] args) {
        MyLock lock = new MyLock();
        new Thread(() -> {
            lock.lock();
            try {
                log.debug("locking");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }finally {
                log.debug("unlocking");
                lock.unlock();
            }
        },"t1").start();

        new Thread(() -> {
            lock.lock();
            try {
                log.debug("locking");
            }finally {
                log.debug("unlocking");
                lock.unlock();
            }
        },"t2").start();
    }
}
// 自定义锁（不可重入锁）
class MyLock implements Lock {

    // 独占锁 同步器类
    class MySync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int arg) {
            // 0-未加锁 1-加锁
            if (compareAndSetState(0,1)) {
                // 加了锁，设置当前线程未owner线程
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            // 设置owner线程为空,该行放在前面才会加写屏障，是其他线程可见
            setExclusiveOwnerThread(null);
            // volatile操作
            setState(0);
            return true;
        }

        @Override
        protected boolean isHeldExclusively() { // 是否持有独占锁
            return getState() == 1;
        }

        public Condition newCondition() {
            return new ConditionObject();
        }
    }

    private MySync sync = new MySync();

    @Override
    public void lock() { // 加锁（不成功会进入等待队列）
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException { // 加锁，可打断
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() { // 尝试加锁（一次）
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException { // 尝试加锁（带超时时间）
        return sync.tryAcquireNanos(1,unit.toNanos(time));
    }

    @Override
    public void unlock() { // 解锁
        sync.release(1);
    }

    @Override
    public Condition newCondition() { // 创建条件变量
        return sync.newCondition();
    }
}
