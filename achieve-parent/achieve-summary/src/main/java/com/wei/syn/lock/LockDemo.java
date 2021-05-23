package com.wei.syn.lock;

import java.util.Queue;
import java.util.concurrent.locks.Lock;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年05月21日
 */
public class LockDemo {

    private Lock lock;
    private Queue queue;
    private int maxSize;

    public LockDemo(Lock lock, Queue queue, int maxSize) {
        this.lock = lock;
        this.queue = queue;
        this.maxSize = maxSize;
    }


}
