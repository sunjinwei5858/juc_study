package com.sunjinwei.singleton;

/**
 * 静态内部类:
 * 1。静态内部类只会在调用的时候 才去加载，不会占用内存。即Demo05第一次加载，并不会加载Demo05Holder，只有当getInstance方法被调用的时候才会去加载Demo05Holder。
 * 2。这种方法保证线程安全，也能保证单例的唯一性，同时也延迟了对象的加载，不会占用内存。
 * 3。这种方式是如何保证线程安全的呢？
 * 分析：从类加载机制分析，【深入理解jvm这本书中写了】
 * 1。类加载的时机，7个阶段：加载 验证 准备 解析 初始化 使用 卸载 【加载-验证-准备-初始化-卸载 这5个阶段的顺序是确定的】
 * 2。JAVA虚拟机在有且仅有的5种场景下会对类进行初始化，以下五种场景都属于主动引用
 *  1。遇到new、getstatic、setstatic或者invokestatic这4个字节码指令时
 *      new一个关键字或者一个实例化对象时
 *      读取或设置一个静态字段时【final修饰、已在编译期把结果放入常量池的除外，已经验证过这个demo】
 *      设置一个静态字段时
 *      调用一个类的静态方法时
 *  2。使用java.lang.reflect包的方法对类进行反射调用的时候，如果类没进行初始化，需要先调用其初始化方法进行初始化。
 *  3。当初始化一个类时，如果其父类还未进行初始化，会先触发其父类的初始化
 *  4。当虚拟机启动时，用户需要指定一个要执行的主类(包含main()方法的类)，虚拟机会先初始化这个类。
 *  5。当使用JDK 1.7等动态语言支持时，如果一个java.lang.invoke.MethodHandle实例最后的解析结果REF_getStatic、REF_putStatic、REF_invokeStatic的方法句柄，
 *  并且这个方法句柄所对应的类没有进行过初始化，则需要先触发其初始化。
 *
 */
public class Demo05 {


    private Demo05() {
    }

    private static class Demo05Holder {
        private static Demo05 demo05 = new Demo05();
    }


    public static Demo05 getInstance() {
        return Demo05Holder.demo05;
    }


}
