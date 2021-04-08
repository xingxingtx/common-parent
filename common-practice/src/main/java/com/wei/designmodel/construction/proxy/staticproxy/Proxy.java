package com.wei.designmodel.construction.proxy.staticproxy;

/**
 * @Describe Proxy
 * @Author a_pen
 * @Date 2020年08月31日
 */
public class Proxy<T extends ISubject> implements ISubject{
    /**代理对象*/
    private T object;

    public Proxy(T object) {
        this.object = object;
    }

    @Override
    public void request() {
        before();
        object.request();
        after();
    }

    private void after() {
        System.out.println("after doing somethings ....");
    }

    private void before() {
        System.out.println("before doing somethings ....");
    }


}
