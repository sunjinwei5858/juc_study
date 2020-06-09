package com.sunjinwei.deadlock;

import java.util.concurrent.TimeUnit;

public class HoldLockThread implements Runnable {

    private String lockA;

    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "持有锁" + lockA + "  尝试获取锁" + lockB);

            try {
                Thread.sleep(TimeUnit.SECONDS.toSeconds(1));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "持有锁" + lockB + "  尝试获取锁" + lockA);

            }
        }

    }
}
