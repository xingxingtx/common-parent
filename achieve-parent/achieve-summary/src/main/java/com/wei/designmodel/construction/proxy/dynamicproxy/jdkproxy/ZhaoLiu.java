package com.wei.designmodel.construction.proxy.dynamicproxy.jdkproxy;


/**
 * @Author wei.peng.
 */
public class ZhaoLiu implements IPerson {

    @Override
    public void findLove() {
        System.out.println("赵六要求：有车有房学历高");
    }

    @Override
    public void buyInsure() {
        System.out.println("zhaoliu  buyInsure");
    }

}
