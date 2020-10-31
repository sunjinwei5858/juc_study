package com.sunjinwei.singleton;

/**
 * 改进方式一：
 * <p>
 * 第二种方式：创建单例模式 为了防止两个线程同时进入if判断，创建两个对象，所以进行加锁
 */
public class Demo02 {
    // 构造只能私有
    private Demo02() {
    }

    private static Demo02 instance = null;

    /**
     * 改进：加锁后进行判断即：加锁-->判断
     *
     * @return
     */
    public static Demo02 getInstance() {
        synchronized (Demo02.class) {
            if (instance == null) {
                instance = new Demo02();
            }
        }
        return instance;
    }
}
