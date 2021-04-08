package com.wei.designmodel.construction.proxy.staticproxy;

/**
 * @Describe RealSubject
 * @Author a_pen
 * @Date 2020年08月31日
 */
public class RealSubject implements ISubject{
    @Override
    public void request() {
        System.out.println("RealSubject doing somethings here ....");
    }
}
