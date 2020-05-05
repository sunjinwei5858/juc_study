package com.sunjinwei.lock.reentrant;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁（递归锁）ReentrantLock和Synchronized
 * case1:Synchronized
 * <p>
 * case2:ReentrantLock
 */
public class ReentrantLockDemo {
    public static void main(String[] args) {

        Demo01 demo01 = new Demo01();

        /**
         * case1
         */
        new Thread(() -> {
            demo01.method01();
        }, "tt01").start();

        new Thread(() -> {
            demo01.method01();
        }, "tt02").start();

        try {
            TimeUnit.MILLISECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        /**
         * case2
         */
        new Thread(() -> {
            demo01.method04();
        }, "tt03"
        ).start();

        new Thread(() -> {
            demo01.method04();
        }, "tt04"
        ).start();

    }
}

class Demo01 {

    /**
     * case1: 使用synchronized method01继续调用同步方法method02方法
     */
    public synchronized void method01() {
        System.out.println(Thread.currentThread().getName() + "##method01");
        method02();
    }

    public synchronized void method02() {
        System.out.println(Thread.currentThread().getName() + "---------method02");
    }

    /**
     * case2: 使用ReentrantLock
     */

    Lock lock = new ReentrantLock();

    public void method04() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "method04");
            method05();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void method05() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "method05");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}
