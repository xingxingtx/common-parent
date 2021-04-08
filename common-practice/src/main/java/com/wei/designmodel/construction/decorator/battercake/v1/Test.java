package com.wei.designmodel.construction.decorator.battercake.v1;

/**
 * @Author wei.peng
 */
public class Test {

    public static void main(String[] args) {
        Battercake battercake = new Battercake();
        System.out.println(battercake.getMsg() + ",总价：" + battercake.getPrice());

        BattercakeWithEgg battercakeWithEgg = new BattercakeWithEgg();
        System.out.println(battercakeWithEgg.getMsg() + ",总价：" + battercakeWithEgg.getPrice());

        BattercakeWithEggAndSauage battercakeWithEggAndSauage = new BattercakeWithEggAndSauage();
        System.out.println(battercakeWithEggAndSauage.getMsg() + ",总价：" + battercakeWithEggAndSauage.getPrice());
    }

}
