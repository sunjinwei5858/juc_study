package com.sunjinwei.blockqueue.producerconsumer.vsersion2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock+condition 实现精确唤醒
 * 需求：A线程打印5份，B线程打印10份，C线程打印15份
 */
public class LockConditionVersion {

}

/**
 * 共享资源类
 */
class DataShareLock {

    private int number = 1;

    private Lock lock = new ReentrantLock();

    private Condition A = lock.newCondition();
    private Condition B = lock.newCondition();
    private Condition C = lock.newCondition();

    public void printA(){
        lock.lock();
        try {

        }catch (Exception e){

        }
    }

}
