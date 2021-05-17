package com.wei.designmodel.construction.proxy.client;


import com.wei.designmodel.construction.proxy.MyInvocationHandler;
import com.wei.designmodel.construction.proxy.MyProxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
*@describe  PeopleProxy
*@author  wei.peng
*@date  2020 05/09
*/
public class PeopleProxy implements MyInvocationHandler {

   public Object getInstance(Class cl){
        return MyProxy.newProxyInstance(cl.getClassLoader(), cl.getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        before();
        Object result = method.invoke(proxy, args);
        after();
        return result;
    }

    private void after() {
        System.out.println("after------");
    }

    private void before() {
        System.out.println("before----- ");
    }
}
