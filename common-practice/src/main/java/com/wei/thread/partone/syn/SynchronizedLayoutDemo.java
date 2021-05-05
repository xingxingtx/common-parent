package com.wei.thread.partone.syn;

import org.openjdk.jol.info.ClassLayout;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年04月10日
 */
public class SynchronizedLayoutDemo {
    public static void main(String[] args) {
        SynchronizedLayoutDemo demo = new SynchronizedLayoutDemo();
        System.out.println(ClassLayout.parseInstance(demo).toPrintable());
        System.out.println("---------------------------------------");
        synchronized (demo){
            /**轻量级锁*/
            System.out.println(ClassLayout.parseInstance(demo).toPrintable());
        }
    }
}
