package com.sunjinwei.load;


/**
 * java的类加载机制学习：【主要是深入理解java虚拟机这本书的demo】
 * 场景1：子类调用父类的static字段，自己本身不会初始化，但是父类会初始化。因为java虚拟机规范 对于静态字段，只有定义这个字段的类才会被初始化。子类只是调用父类，只会触发父类的初始化。
 * 场景2：子类调用父类的final+static字段，都不会初始化，因为被final修饰，在编译阶段相当于这个常量放在了Demo01调用类的常量池了!!!
 */
public class Demo01 {

    public static void main(String[] args) {
        // 测试static
        //int num = SubChild.num;

        //测试final+static
        //int result = SubChild.result;
    }

}


class Parent {

    static {
        System.out.println("父类初始化");
    }

    /**
     * only static
     */
    public static int num = 100;

    /**
     * static+final 【有坑】：被final修饰属于常量了，常量在编译阶段会存入调用类比如Demo01的常量池中，本质上没有直接引用到定义常量的类，因此不会触发到定义常量的类的初始化
     */
    public static final int result = 100;

}

class SubChild extends Parent {

    static {
        System.out.println("子类初始化");
    }

}
