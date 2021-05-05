package com.wei.thread.partone.syn;

/**
 * 1.锁的范围
 * 实例锁 demo demo0
 * 类锁修饰静态方法 demo1,demo2
 * 代码块
 * 轻量级锁（00），重量级锁（10），偏向锁（01：计算了hashCode的情况下无法使用偏向锁）
 * @Describe
 * @Author wei.peng
 * @Date 2021年04月10日
 */
public class SynchronizedDemo {
    /**实例对象锁*/
    synchronized void demo(){
        //doing somethings
    }

     void demo0(){
        synchronized (this){}
        //doing somethings
    }

    synchronized static void demo1(){

    }

     static void demo2(){
        synchronized (SynchronizedDemo.class){

        }
    }

    public static void main(String[] args) {
        SynchronizedDemo demo = new SynchronizedDemo();
        SynchronizedDemo demo1 = new SynchronizedDemo();

    }
}
