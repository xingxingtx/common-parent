package com.wei.partone.syn;

import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年04月10日
 */
public class Consumer implements Runnable {
    private Queue<String> queue;
    private int maxSize;
    private Lock lock;
    private Condition condition;

    public Consumer(Queue<String> queue, int maxSize) {
        this.queue = queue;
        this.maxSize = maxSize;
    }

    public Consumer(Queue<String> queue, int maxSize, Lock lock, Condition condition) {
        this.queue = queue;
        this.maxSize = maxSize;
        this.lock = lock;
        this.condition = condition;
    }



 /*   @Override
    public void run() {
        while (true) {
            synchronized (queue) {
                while (queue.isEmpty()) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("消费者消费:"+ queue.remove());
                queue.notify();
            }
        }
    }*/

    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();
                while (queue.isEmpty()) {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("消费者消费:" + queue.remove());
                condition.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
