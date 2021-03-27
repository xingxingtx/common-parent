package com.wei.designmodel.construction.decorator.battercake.v2;

/**
 * @Author wei.peng
 */
public class SauageDecorator extends BattercakeDecorator{

    public SauageDecorator(Battercake battercake) {
        super(battercake);
    }

    protected String getMsg(){ return super.getMsg() + "1根香肠";}

    public int getPrice(){ return super.getPrice() + 2;}

}
