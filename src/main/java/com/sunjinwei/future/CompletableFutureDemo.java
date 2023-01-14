package com.sunjinwei.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @program: com.sunjinwei.future
 * @author: sun jinwei
 * @create: 2023-01-04 09:00
 * @description: CompletableFuture学习
 **/
public class CompletableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(
                () -> {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return 3;
                }
        );

        System.out.println(completableFuture.join());

        System.out.println(Thread.currentThread().getName() + "哈哈哈哈");


    }

}