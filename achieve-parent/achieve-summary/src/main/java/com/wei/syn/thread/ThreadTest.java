package com.wei.syn.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年05月31日
 */
public class ThreadTest {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
            Thread t1 = new Thread(new ThreadA());
            Thread t2 = new Thread(new ThreadA());
            t1.start();
            t2.start();
    }

    public static class ThreadA extends Thread{
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }
}
