package com.wei.designmodel.construction.proxy;

/**
*@describe  MyClassLoader
*@author  wei.peng
*@date  2020 05/09
*/
public class MyClassLoader  extends ClassLoader{

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }
}
