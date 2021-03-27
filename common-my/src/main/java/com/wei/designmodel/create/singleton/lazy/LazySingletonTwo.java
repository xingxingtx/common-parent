package com.wei.designmodel.create.singleton.lazy;

/**
 *@describe  LazySingletonTwo
 *@author  wei.peng
 *@date  2020 05/14
 * 线程安全，执行效率低，代码复杂。
 */
public class LazySingletonTwo {
    public static LazySingletonTwo singleton = null;

    private LazySingletonTwo(){}

    public  static LazySingletonTwo getInstance(){
        /**检查是否需要锁*/
        if(singleton == null){
            synchronized (singleton){
                /**检查对象是否为null*/
                if(singleton == null){
                    singleton = new LazySingletonTwo();
                }
            }
        }
        return singleton;
    }
}
