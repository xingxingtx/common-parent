package com.wei.designmodel.create.singleton.lazy;

/**
 * @author wei.peng
 * @descripe
 * @Date 2020年08月05日
 * 线程安全，能被反射或者反序列化获取多个实例
 */
public class LazyInnerClassSingleton {
    private LazyInnerClassSingleton() {
        if(LazyHelper.singleton != null){
            throw new RuntimeException("不允许创建多个实例");
        }
    }

    public static LazyInnerClassSingleton getInstance(){
        return LazyHelper.singleton;
    }
    private static class LazyHelper {
        private static final LazyInnerClassSingleton singleton = new LazyInnerClassSingleton();
    }

    /**防止以流的方式获取实例*/
    private Object readResolve(){
        return LazyHelper.singleton;
    }
}
