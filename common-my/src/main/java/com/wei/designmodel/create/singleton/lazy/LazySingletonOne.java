package com.wei.designmodel.create.singleton.lazy;

/**
 *@describe  LazySingletonOne
 *@author  wei.peng
 *@date  2020 05/14
 * 线程安全，执行效率低。
 */
public class LazySingletonOne {
    public static LazySingletonOne singleton = null;

    private LazySingletonOne(){}

    public synchronized static LazySingletonOne getInstance(){
        if(singleton == null){
            singleton = new LazySingletonOne();
        }
        return singleton;
    }
}
