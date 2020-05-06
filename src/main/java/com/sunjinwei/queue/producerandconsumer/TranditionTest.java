package com.sunjinwei.queue.producerandconsumer;

import java.util.concurrent.TimeUnit;

public class TranditionTest {
    public static void main(String[] args) {
        ReentranLockStock reentranLockStock = new ReentranLockStock();

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                reentranLockStock.consumer();
            }, "BBB").start();

            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                reentranLockStock.produce();
            }, "AAA").start();
        }
    }
}
