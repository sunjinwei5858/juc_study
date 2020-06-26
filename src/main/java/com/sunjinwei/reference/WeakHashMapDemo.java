package com.sunjinwei.reference;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * WeakHashMap：发生gc时，回收key为null的键值对
 * 注意与HashMap进行对比: HashMap的“键”是“强引用(StrongReference)”，而WeakHashMap的键是“弱引用(WeakReference)”。
 * <p>
 * WeakReference的“弱键”能实现WeakReference对“键值对”的动态回收。
 * 当“弱键”不再被使用到时，GC会回收它，WeakReference也会将“弱键”对应的键值对删除。
 * <p>
 * 原理：通过WeakReference(弱引用)和ReferenceQueue(引用队列)实现的
 * <p>
 * 第一，“键”是WeakReference，即key是弱键。
 * 第二，ReferenceQueue是一个引用队列，它是和WeakHashMap联合使用的。当弱引用所引用的对象被垃圾回收，
 * Java虚拟机就会把这个弱引用加入到与之关联的引用队列中。 WeakHashMap中的ReferenceQueue是queue。
 * 第三，WeakHashMap是通过数组实现的，我们假设这个数组是table。
 * <p>
 * 方便做高速缓存和对内存敏感的信息处理
 */
public class WeakHashMapDemo {
    public static void main(String[] args) {
        myHashMap();

        System.out.println("===================");

        myWeakHashMap();


    }

    /**
     * 弱引用的WeekHashMap
     * <p>
     * When a key has been discarded its entry is effectively removed from the map,
     * so this class behaves somewhat differently
     * <p>
     * 如果key为null时，那么手动触发gc时，会进行回收
     */
    private static void myWeakHashMap() {
        WeakHashMap<Integer, String> weakHashMap = new WeakHashMap<>();
        Integer key = new Integer(2);
        String value = "weakHashMap";

        weakHashMap.put(key, value);

        System.out.println(weakHashMap);

        /**
         * 只有当key不再被使用时，发生gc会被回收
         */
        key = null;

        System.out.println(weakHashMap);


        System.gc();

        System.out.println("发生gc：" + weakHashMap);


    }

    /**
     * 原始的HashMap特性
     */
    private static void myHashMap() {
        HashMap<Integer, String> hashMap = new HashMap<>();
        Integer key = new Integer(1);
        String value = "hashmap";
        hashMap.put(key, value);

        System.out.println(hashMap);

        /**
         * 当将key设置为null时，因为hashMap底层是一个Node数组，这里将key对象设置为null，跟hashmap里面的key木有关系
         */
        key = null;

        System.out.println(hashMap);

        System.gc();

        /**
         * 手动发生gc，也不会对hashmap有啥影响
         */
        System.out.println(hashMap);


    }

}
