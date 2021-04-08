package com.wei.designmodel.create.singleton.hungry;

/**
 * @author wei.peng
 * @describe HungrySingleton
 * @date 2020 05/13
 * 饿汉模式，线程安全，不适合有大量单例创建的情况使用，浪费内存，能被反射或者反序列化获取多个实例
 */
public class HungrySingleton {
    private static  HungrySingleton singleton = new HungrySingleton();

    private HungrySingleton(){}

    public static HungrySingleton getInstance(){
        return singleton;
    }
}
