package com.sunjinwei.queue.producerandconsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentranLockStock implements Stock {

    private List<String> list = new ArrayList<>();

    private Lock lock = new ReentrantLock();

    // 容器不为空 唤醒消费者进行消费
    private Condition consumerCondition = lock.newCondition();

    // 容器还没满 唤醒生产者生产商品
    private Condition produceCondition = lock.newCondition();

    @Override
    public String produce() {
        try {
            lock.lock();
            while (list.size() == size) {
                // 容器满了 唤醒消费者线程 消费商品；阻塞生产者线程
                System.out.println("商品满了");
                produceCondition.await();
            }
            System.out.println(Thread.currentThread().getName() + "开始生产商品中....");
            // 结束等待时 进行生产商品
            list.add("aaa");
            list.add("bbb");
            list.add("ccc");
            TimeUnit.SECONDS.sleep(2);

            // 通知消费者可以继续消费
            consumerCondition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return list.toString();
    }

    @Override
    public void consumer() {
        try {
            lock.lock();
            while (list.isEmpty()) {
                System.out.println("商品不够了。。");
                // 容器空了 消费者进行等待
                consumerCondition.await();
            }
            // 结束等待时 进行消费商品
            System.out.println(Thread.currentThread().getName() + "========开始消费商品中....");
            list.clear();
            TimeUnit.SECONDS.sleep(2);
            // 消费完成通知生产者继续生产
            produceCondition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}
