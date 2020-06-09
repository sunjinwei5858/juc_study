package com.sunjinwei.deadlock;

/**
 * 死锁
 * 产生原因
 * 代码
 * 如何解决
 */
public class DeadLockDemo {
    public static void main(String[] args) {
        String a = "aaaa";
        String b = "bbb";


        new Thread(new HoldLockThread(a, b), "线程aaaa").start();
        new Thread(new HoldLockThread(b, a), "线程bbb").start();


    }
}
