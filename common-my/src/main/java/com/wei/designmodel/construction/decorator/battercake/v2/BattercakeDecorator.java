package com.wei.designmodel.construction.decorator.battercake.v2;

/**
 * @Author wei.peng
 */
public class BattercakeDecorator extends Battercake{


    private Battercake battercake;

    public BattercakeDecorator(Battercake battercake) {
        this.battercake = battercake;
    }

    protected String getMsg(){ return this.battercake.getMsg();}

    public int getPrice(){ return this.battercake.getPrice();}

}
