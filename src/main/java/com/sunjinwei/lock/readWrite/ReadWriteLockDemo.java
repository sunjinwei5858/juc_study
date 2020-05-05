package com.sunjinwei.lock.readWrite;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁：读写分离
 * 写操作需要保证原子性
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) {

        MyCache myCache = new MyCache();

        /**
         * 写
         */
        for (int i = 1; i <= 5; i++) {
            final int num = i;
            new Thread(() -> {
                myCache.put(num + "", num + "");
            }, i + "").start();
        }

        /**
         * 读
         */
        for (int i = 1; i <= 5; i++) {
            final int num = i;
            new Thread(() -> {
                myCache.get(num + "");
            }, i + "").start();
        }
    }
}

/**
 * 缓存使用的就是读写锁
 */

class MyCache {
    // volatile 关键字保证可见性 只要有更新 那么一定要通知其他线程
    private volatile Map<String, Object> map = new HashMap<>();

    private ReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    /**
     * 写操作使用写锁
     *
     * @param key
     * @param value
     */
    public void put(String key, Object value) {
        reentrantReadWriteLock.writeLock().lock();
        System.out.println(Thread.currentThread().getName() + "写入开始..." + key);
        map.put(key, value);
        System.out.println(Thread.currentThread().getName() + "写入完成...");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reentrantReadWriteLock.writeLock().unlock();
        }
    }

    public void get(String key) {
        reentrantReadWriteLock.readLock().lock();
        System.out.println(Thread.currentThread().getName() + "读取开始...");
        Object result = map.get(key);
        System.out.println(Thread.currentThread().getName() + "读取完成..." + result);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        reentrantReadWriteLock.readLock().unlock();
    }
}


