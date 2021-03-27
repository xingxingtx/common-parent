package com.wei.designmodel.construction.proxy.dynamicproxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Describe AbstractJdkProxy
 * @Author a_pen
 * @Date 2020年08月31日
 */
public  class JdkProxy<T extends IPerson> implements InvocationHandler {

    private T object;

    public T getInstance(T target) {
        this.object = target;
        Class<? extends IPerson> aClass = target.getClass();
        return (T) Proxy.newProxyInstance(aClass.getClassLoader(), aClass.getInterfaces(),  this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object invoke = method.invoke(this.object, args);
        after();
        return invoke;
    }

    public  void before() {
        System.out.println("jdkproxy before 。。。。");
 
    }

    private void after() {
        System.out.println("jdkproxy after 。。。。");
    }


}
