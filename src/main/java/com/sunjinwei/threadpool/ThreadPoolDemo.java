package com.sunjinwei.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 1。线程池如何使用？ Executors和ThreadPoolExecutor
 * 2。七大重要参数
 * 3。线程池底层工作原理
 * 4。线程池的四大拒绝策略
 * 5。你在工作中如何使用线程池，是否自定义过线程池的使用
 * 6。合理配置线程池是如何考虑的？
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        /**
         * 自定义线程池 7大参数
         * 1核心数
         * 2最大线程数
         * 3存活时间
         * 4存活时间单位
         * 5阻塞队列 如果不指定长度 那么默认为Integer.MAX_VALUE
         * 6线程池工厂
         * 7拒绝策略
         */
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                2,
                3,
                2,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
        try {
            for (int i = 0; i < 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "执行了");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
