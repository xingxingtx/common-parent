package com.wei.partone;

import java.util.concurrent.TimeUnit;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年04月05日
 */
public class UseThreadDemo {
    public static void main(String[] args) {
        new Thread(()->{
            while (true){
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "thread--001").start();

        new Thread(()->{
            while (true){
                try {
                    synchronized (UseThreadDemo.class){
                        UseThreadDemo.class.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "thread--002").start();
        new Thread(new BlockDemo(), "BlockDemo -- 001").start();
        new Thread(new BlockDemo(), "BlockDemo -- 002").start();
    }

    static class BlockDemo extends Thread{
        @Override
        public void run() {
            while (true){
                try {
                    synchronized (BlockDemo.class){
                        TimeUnit.SECONDS.sleep(100);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
