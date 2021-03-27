package com.wei.designmodel.construction.proxy.staticproxy;

/**
 * @author a_pen
 */
public class Test {
    public static void main(String[] args) {
        Proxy proxy = new Proxy(new RealSubject());
        proxy.request();
    }
}
