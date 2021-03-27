package com.wei.designmodel.construction.proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
*@describe  InvocationHandler
*@author  wei.peng
*@date  2020 05/09
*/
public interface MyInvocationHandler {

    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException;
}
