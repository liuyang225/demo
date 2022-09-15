package com.example.demo.thread.Atomic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 原子引用类型
 */
public class Test2 {
    public static void main(String[] args) {
        DecimalAccount.demo(new DecimalAccountcas(new BigDecimal("100")));
    }
}

class DecimalAccountcas implements DecimalAccount {

    private AtomicReference<BigDecimal> balance;

    public DecimalAccountcas(BigDecimal balance) {
        this.balance = new AtomicReference<>(balance);
    }

    @Override
    public BigDecimal getBalance() {
        return balance.get();
    }

    @Override
    public void withDraw(BigDecimal amount) {
        BigDecimal prev = balance.get();
        BigDecimal next = prev.subtract(amount);
        while (true) {
            if (balance.compareAndSet(prev,next)) {
                break;
            }
        }
    }
}

interface DecimalAccount{
    // 查看余额
    BigDecimal getBalance();

    // 取款
    void withDraw(BigDecimal amount);

    static void demo(DecimalAccount account) {
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Thread(() -> {
                account.withDraw(BigDecimal.TEN);
            }));
        }
        list.forEach(Thread::start);
        list.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(account.getBalance());
    }
}
