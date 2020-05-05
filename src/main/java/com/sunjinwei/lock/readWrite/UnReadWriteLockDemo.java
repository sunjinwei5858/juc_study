package com.sunjinwei.lock.readWrite;

import java.util.HashMap;
import java.util.Map;

/**
 * 不使用ReentrantReadWriteLock读写锁 演示
 */
public class UnReadWriteLockDemo {
    public static void main(String[] args) {

        MyUnCashe myUnCashe = new MyUnCashe();

        for (int i = 0; i < 5; i++) {
            final int tmep = i;
            new Thread(() -> {
                myUnCashe.put(tmep + "", tmep + "");
            }, tmep + "").start();
        }

    }
}

class MyUnCashe {

    private volatile Map<String, Object> map = new HashMap<>();

    /**
     * 写
     *
     * @param key
     * @param value
     */
    public void put(String key, Object value) {
        System.out.println(Thread.currentThread().getName() + "写入开始");
        map.put(key, value);
        System.out.println(Thread.currentThread().getName() + "写入完成");
    }

    /**
     * 读操作
     *
     * @param key
     */
    public void get(String key) {
        System.out.println(Thread.currentThread().getName() + "读取开始...");
        Object result = map.get(key);
        System.out.println(Thread.currentThread().getName() + "读取完成：" + result);
    }
}
