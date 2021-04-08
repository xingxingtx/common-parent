package com.wei.designmodel.construction.proxy.dynamicproxy.gpproxy.client;


import com.wei.designmodel.construction.proxy.dynamicproxy.gpproxy.proxy.GPClassLoader;
import com.wei.designmodel.construction.proxy.dynamicproxy.gpproxy.proxy.GPInvocationHandler;
import com.wei.designmodel.construction.proxy.dynamicproxy.gpproxy.proxy.GPProxy;

import java.lang.reflect.Method;

/**
 * @Author wei.peng.
 */
public class GpMeipo implements GPInvocationHandler {
    private IPerson target;
    public IPerson getInstance(IPerson target){
        this.target = target;
        Class<?> clazz =  target.getClass();
        return (IPerson) GPProxy.newProxyInstance(new GPClassLoader(),clazz.getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(this.target,args);
        after();
        return result;
    }

    private void after() {
        System.out.println("双方同意，开始交往");
    }

    private void before() {
        System.out.println("我是媒婆，已经收集到你的需求，开始物色");
    }
}
