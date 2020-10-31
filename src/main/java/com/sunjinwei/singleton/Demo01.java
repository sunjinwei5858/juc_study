package com.sunjinwei.singleton;

/**
 * 第一种方式：创建单例模式 只适用于单线程
 * 如果两个线程同时进入 if (instance == null) 判断，那么就会导致两个线程都创建对象
 */
public class Demo01 {
    // 构造只能私有
    private Demo01() {
    }

    private static Demo01 instance = null;

    public static Demo01 getInstance() {
        if (instance == null) {
            instance = new Demo01();
        }
        return instance;
    }
}
