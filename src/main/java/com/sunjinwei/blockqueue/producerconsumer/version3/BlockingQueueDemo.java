package com.sunjinwei.blockqueue.producerconsumer.version3;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class BlockingQueueDemo {
    public static void main(String[] args) {
        DataSource dataSource = new DataSource(new ArrayBlockingQueue(5));
        new Thread(() -> {
            dataSource.product();
        }, "AAA").start();

        new Thread(() -> {
            dataSource.consumer();
        }, "BBB").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        dataSource.stop();
    }


}

/**
 * 使用阻塞队列实现线程通信-生产者/消费者模式
 * volatile/cas/atomicInteger/线程交互
 * flag = true 生产一个 消费一个
 */

class DataSource {
    // 保证每个线程对flag可见性
    private volatile Boolean FLAG = true;

    // 使用atomicInteger 原子引用
    private AtomicInteger atomicInteger = new AtomicInteger();

    // 构造传入阻塞队列接口 为了适配 不传阻塞队列具体的类
    private BlockingQueue blockingQueue;

    public DataSource(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    /**
     * 生产者
     */
    public void product() {
        // 使用while循环 进行判断flag 如果为true 那么进行生产
        while (FLAG) {
            System.out.println(Thread.currentThread().getName() + "  开始生产。。");
            // 开始生产 进行++操作 1s中生产一个
            int increment = atomicInteger.getAndIncrement();
            try {
                boolean b = blockingQueue.offer(increment, 2, TimeUnit.SECONDS);
                if (b) {
                    System.out.println(Thread.currentThread().getName() + " 生产蛋糕成功为:" + increment);
                } else {
                    System.out.println(Thread.currentThread().getName() + " 生产蛋糕失败为:" + increment);
                }
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 消费者
     */
    public void consumer() {
        Object result;
        while (FLAG) {
            System.out.println(Thread.currentThread().getName() + "  开始消费");
            try {
                result = blockingQueue.poll(2, TimeUnit.SECONDS);
                // 如果两秒中还没有蛋糕 说明停止生产 不再进行消费
                if (result == null) {
                    System.out.println(Thread.currentThread().getName() + " 2秒钟消费不到蛋糕了 改为false");
                    FLAG = false;
                } else {
                    System.out.println(Thread.currentThread().getName() + " 消费的蛋糕为 " + result);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 结束 将flag设置为false即可
     */
    public void stop() {
        FLAG = false;
    }


}
