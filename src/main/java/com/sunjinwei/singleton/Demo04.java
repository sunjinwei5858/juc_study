package com.sunjinwei.singleton;

/**
 * 方式三改进：著名的双重锁失效问题 即DCL失效问题，
 * 因为jvm会进行指令重排序，所以jvm可能在堆内存还没实例化参数，先执行了将instance指向了堆内存
 */
public class Demo04 {
    // 构造只能私有
    private Demo04() {
    }

    /**
     * 改进：加入volatile关键字，每次都从主存中进行获取
     */
    private volatile static Demo04 instance = null;

    /**
     * 改进:防止指令重排序，加入volatile关键字，jdk1.6
     *
     * @return
     */
    public static Demo04 getInstance() {
        if (instance == null) {
            synchronized (Demo04.class) {
                if (instance == null) {
                    /**
                     * 重排序问题分析：
                     * instance = new Demo04();
                     * 这里jvm进行了三个步骤：
                     * 1。在堆内存中开辟空间
                     * 2。在堆内存中实例化Demo04的里面的各个参数
                     * 3。把instance对象指向堆内存
                     *  因为jvm可能2还没执行就执行了3，那么别的线程就会以为对象已经实例化出来了，!=null了，直接拿出来用，那么就会出现问题
                     */
                    instance = new Demo04();
                }
            }
        }
        return instance;
    }
}
