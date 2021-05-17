package com.wei.designmodel.construction.flyweight.general;

/**
 * @Describe 抽象享元角色
 * @Author a_pen
 * @Date 2020年09月07日
 */
public interface IFlyweight {

    /**
     *  抽象操作
     * @param extrinsicState
     */
    public void operation(String extrinsicState);

}
