package com.wei.designmodel.create.singleton.lazy;

/**
 * @author wei.peng
 * @describe LazySingleton
 * @date 2020 05/13
 * 线程不安全，能被反射或者反序列化获取多个实例
 */
public class LazySingleton {
    public static LazySingleton singleton = null;

    private LazySingleton(){}

    public static LazySingleton getInstance(){
        if(singleton == null){
            singleton = new LazySingleton();
        }
        return singleton;
    }
}
