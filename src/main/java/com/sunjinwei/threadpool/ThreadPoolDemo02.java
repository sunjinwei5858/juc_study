package com.sunjinwei.threadpool;

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
       // ExecutorService threadPool01 = Executors.newFixedThreadPool(3);
       // ExecutorService threadPool02 = Executors.newSingleThreadExecutor(3);


    }
}
