package com.wei.designmodel.create.singleton.hungry;

/**
*@describe  HungrySingletonTwo
*@author  wei.peng
*@date  2020 05/14
 * 饿汉模式，线程安全，不适合有大量单例创建的情况使用，浪费内存，能被反射或者反序列化获取多个实例
*/
public class StaticHungrySingleton {
    private static StaticHungrySingleton singleton = null;

    static {
        if (singleton == null){
            singleton = new StaticHungrySingleton();
        }
    }
    private StaticHungrySingleton(){}

    public static StaticHungrySingleton getInstance(){
        return singleton;
    }
}
