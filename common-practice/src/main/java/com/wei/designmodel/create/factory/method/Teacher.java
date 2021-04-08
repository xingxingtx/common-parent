package com.wei.designmodel.create.factory.method;

/**
 * @author wei.peng
 * @descripe
 * @Date 2019/8/26 0026.
 */
public class Teacher implements FactoryMethod {
    @Override
    public void doSomethings() {
        System.out.println("teacher doing Somethings");
    }
}
