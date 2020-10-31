package com.sunjinwei.load;

/**
 * 场景2：【出自深入理解jvm】
 * 如果调用方是new一个数组，这个数组的类型是这个对象，那么这个对象也不会初始化的. 【通过数组引用该类，不会导致该类初始化】
 */
public class Demo02 {
    public static void main(String[] args) {
        Child[] array = new Child[10];

    }
}

class Child {

    static {
        System.out.println("Child 初始化");
    }

}
