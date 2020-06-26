package com.sunjinwei.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * 引用队列
 */
public class ReferenceQueueDemo {
    public static void main(String[] args) {
        Object objec = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        WeakReference<Object> weakReference = new WeakReference<>(objec, referenceQueue);

        System.out.println(objec);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());

        System.out.println("=============");

        /**
         * 设置为null，并且手动触发gc
         */
        objec = null;
        System.gc();

        System.out.println(objec);
        System.out.println(weakReference.get());

        /**
         * 弱引用发生gc时，会将弱引用对象放置到引用队列中，需要时间 所以这里需要将线程睡一会
         */
        try {
            Thread.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(referenceQueue.poll());
    }
}
