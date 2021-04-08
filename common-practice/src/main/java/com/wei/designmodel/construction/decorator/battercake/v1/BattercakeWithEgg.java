package com.wei.designmodel.construction.decorator.battercake.v1;

/**
 * @Author wei.peng
 */
public class BattercakeWithEgg extends Battercake {

    protected String getMsg(){ return super.getMsg() + "+1个鸡蛋";}

    public int getPrice(){ return super.getPrice() + 1;}

}
