package com.sunjinwei.countdownlatch;

import com.sunjinwei.countdownlatch.type.CountryEnum;

import java.util.concurrent.CountDownLatch;

/**
 * 实现需求：秦灭六国 一统天下 必须六国先被灭国 然后秦国才能一统天下 有顺序要求
 * before: 不使用 CountDownLatch，无法实现。
 * after：使用CountDownLatch 进行计数器 进行countDown 减减操作 然后await操作 count=0才会进行
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {

        /**
         * after：使用CountDownLatch
         */
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {

                System.out.println(Thread.currentThread().getName() + "国灭");
                // 进行--操作
                countDownLatch.countDown();

            }, CountryEnum.getCountryByCode(i).getCountryName()).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("秦国一统天下......");

    }

    /**
     * before：不使用countDownlatch 不能实现 六国先被灭 最后秦国一统天下
     */
    private static void unCountDownLatch() {
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {

                System.out.println(Thread.currentThread().getName() + "国灭");

            }, String.valueOf(i)).start();
        }

        System.out.println("秦国一统天下......");
    }

}

