package com.wei.thread.partone.syn;

import java.util.Queue;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年04月10日
 */
public class Consumer implements Runnable{
    private Queue<String> queue;
    private int maxSize;

    public Consumer(Queue<String> queue, int maxSize) {
        this.queue = queue;
        this.maxSize = maxSize;
    }

    @Override
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
    }
}
