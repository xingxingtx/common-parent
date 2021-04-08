package com.wei.designmodel.construction.proxy.dynamicproxy.gpproxy.proxy;

import java.lang.reflect.Method;

/**
 * @Author wei.peng on 2019/3/10.
 */
public interface GPInvocationHandler {
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable;
}
