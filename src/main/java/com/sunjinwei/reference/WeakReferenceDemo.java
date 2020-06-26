package com.sunjinwei.reference;

import java.lang.ref.WeakReference;

/**
 * 弱引用demo
 * 弱引用需要用java.lang.ref.WeakReference类来实现，它比软引用的生存期更短。
 * 对于弱引用对象来说，只要垃圾回收机制一运行，不管JVM的内存空间是否足够，都会回收该对象占用的内存。
 */
public class WeakReferenceDemo {
    public static void main(String[] args) {
        Object o = new Object();

        WeakReference<Object> weakReference = new WeakReference<>(o);

        System.out.println(o);
        System.out.println(weakReference.get());

        // 将object对象设置为null
        o = null;

        System.gc();

        System.out.println("发生gc：" + o);
        System.out.println(weakReference.get());

    }
}
