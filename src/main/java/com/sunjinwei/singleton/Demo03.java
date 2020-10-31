package com.sunjinwei.singleton;

/**
 * 改进方式三：
 * 对于方式二加锁只是为了解决并发问题，重复创建对象，但是如果每次获取对象都要加锁判断一下，那么非常消耗性能
 * 所以可以进行改进：判断-->加锁-->判断
 */
public class Demo03 {
    // 构造只能私有
    private Demo03() {
    }

    private static Demo03 instance = null;

    /**
     * 改进:判断-->加锁-->判断
     *
     * @return
     */
    public static Demo03 getInstance() {
        if (instance == null) {
            synchronized (Demo03.class) {
                if (instance == null) {
                    instance = new Demo03();
                }
            }
        }
        return instance;
    }
}
