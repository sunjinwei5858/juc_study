package com.sunjinwei.lock.spin;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 手写自旋锁
 * 实现方式：cas+while循环
 */
public class SpinLockDemo {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    /**
     * 当前线程获取锁
     */
    public void myLock() {
        Thread thread = Thread.currentThread();
        // 期望值是null ，如果为null 那么抢占资源
        while (!atomicReference.compareAndSet(null, thread)) {

        }
        System.out.println(thread.getName() + "抢占锁...");
    }

    /**
     * 当前线程释放锁
     */
    public void myUnLock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(thread.getName() + "释放锁咯...");
    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        // A线程
        new Thread(() -> {
            spinLockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnLock();
        }, "aa").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("=======");

        // B线程
        new Thread(() -> {
            System.out.println("bb线程试图获取锁");
            spinLockDemo.myLock();
            spinLockDemo.myUnLock();
        }, "bb").start();
    }
}
