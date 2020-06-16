package com.sunjinwei.system;

/**
 * https://segmentfault.com/a/1190000009922279
 * 思考System.copy JVM 提供的数组拷贝实现
 * 1。是深拷贝还是浅拷贝，浅拷贝是拷贝对象的引用
 * 2。修改目标对象的属性值后，源对象有没有影响 会影响之前的对象
 * 3。高效还是低效 与for循环对比
 *  数据量小的时候 两者无差别
 *  数据量大的时候 根据对底层的理解，System.arraycopy是对内存直接进行复制，减少了for循环过程中的寻址时间，从而提高了效能。
 *  System.arraycopy为 JVM 内部固有方法，它通过手工编写汇编或其他优化方法来进行 Java 数组拷贝，
 *  这种方式比起直接在 Java 上进行 for 循环或 clone 是更加高效的。数组越大体现地越明显。
 *
 * Arrays.copyOf() 底层也是使用 System.copy
 *
 */
public class ArrayCopyDemo {
    public static void main(String[] args) {
        User[] arr = new User[2];
        arr[0] = new User(1, "aaa");
        arr[1] = new User(2, "bbb");

        for (User user : arr) {
            System.out.println(user.toString());
        }

        User[] arr02 = new User[2];

        System.arraycopy(arr, 0, arr02, 0, arr.length);

        System.out.println("=============");


        arr02[0].setName("ccc");

        for (int i = 0; i < arr02.length; i++) {
            System.out.println(arr02[i] == arr[i]);
        }

        System.out.println(arr[0].toString());


    }
}
