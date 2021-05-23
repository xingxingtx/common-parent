package com.wei.designmodel.create.singleton;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年05月20日
 */
public class ThreadLocalSingleton {
    private static final ThreadLocal<ThreadLocalSingleton> singletonThreadLocal = ThreadLocal.withInitial(() -> new ThreadLocalSingleton());

    private ThreadLocalSingleton() {
    }

    public static ThreadLocalSingleton getInstance(){
        return singletonThreadLocal.get();
    }
}
