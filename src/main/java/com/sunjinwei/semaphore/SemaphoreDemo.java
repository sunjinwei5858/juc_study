package com.sunjinwei.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 抢车位：
 * 一共6辆车抢3个车位，抢到车位的车停三秒，然后释放车位，剩余没抢到的继续争抢
 * 使用Semaphore 主要用于两个目的，一个是用于多个共享资源的互斥使用，另一个用于并发线程数的控制。
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        // 3个车位 默认为false 非公平锁 效率高
        Semaphore semaphore = new Semaphore(3);

        // 6部车抢3个车位
        for (int i = 1; i <= 6; i++) {
            Thread thread = new Thread(() -> {

                try {
                    // 抢占资源
                    semaphore.acquire();
                    // 说明抢到车位
                    System.out.println(Thread.currentThread().getName() + "抢到车位");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName() + "######离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 释放资源
                    semaphore.release();
                }

            }, String.valueOf(i));

            thread.start();

        }

    }
}
