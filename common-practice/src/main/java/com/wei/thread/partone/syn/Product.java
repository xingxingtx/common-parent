package com.wei.thread.partone.syn;

import java.util.Queue;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年04月10日
 */
public class Product implements Runnable {
    private Queue<String> queue;
    private int maxSize;

    public Product(Queue<String> queue, int maxSize) {
        this.queue = queue;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            synchronized (queue) {
                i++;
                while (queue.size() == maxSize) {
                    try {
                        queue.wait();//阻塞线程，一定会释放锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                queue.add("生产者生产:" + i);
                System.out.println("生产者生产消息："+ i);
                queue.notify();
            }
        }
    }
}
