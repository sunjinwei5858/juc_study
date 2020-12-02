package com.sunjinwei.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * jdk自带的线程池 但是生产上一般不用jdk自带的
 * 线程池不允许使用Executors去创建，而是通过ThreadPoolExecutor的方式，这样的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险。
 * 说明：Executors返回的线程池对象的弊端如下：
 * 1）FixedThreadPool和SingleThreadPool:
 *   允许的请求队列长度为Integer.MAX_VALUE，可能会堆积大量的请求，从而导致OOM。
 * 2）CachedThreadPool:
 *   允许的创建线程数量为Integer.MAX_VALUE，可能会创建大量的线程，从而导致OOM。
 */
public class ThreadPoolDemo02 {
    public static void main(String[] args) {

        // 第一种：执行长期的任务，性能好很多
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        // 第二种：适用：执行很多短期异步的小程序或者负载较轻的服务器
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        // 第三种：一个任务一个任务执行的场景
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        // 第四种：jdk8 新加的
        ExecutorService workStealingPool = Executors.newWorkStealingPool();
        // 第五种：调度的线程池
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(2);

    }
}
