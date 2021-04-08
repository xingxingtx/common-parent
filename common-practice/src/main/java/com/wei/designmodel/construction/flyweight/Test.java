package com.wei.designmodel.construction.flyweight;

import com.wei.designmodel.construction.flyweight.general.FlyweightFactory;
import com.wei.designmodel.construction.flyweight.general.IFlyweight;

/**
 * @Describe Test
 * @Author a_pen
 * @Date 2020年09月07日
 */
public class Test {

    public static void main(String[] args) {

        /**general test -------------start*/
        IFlyweight flyweight = FlyweightFactory.getFlyweight("key");
        flyweight.operation("key operation");
        IFlyweight flyweight1 = FlyweightFactory.getFlyweight("key1");
        flyweight1.operation("key1 operation");
        IFlyweight flyweight2 = FlyweightFactory.getFlyweight("key");
        flyweight2.operation("key operation");
        /**general test -------------end*/

        /**jdk源码应用 test*/
        Integer var1 =  1;
        Integer var2 = 1;
        Integer var3 =  280;
        Integer var4 = 280;
        Integer var5 = Integer.valueOf(1);
        Integer var6 = Integer.valueOf(1);
        Integer var7 =  Integer.valueOf(128);
        Integer var8 = Integer.valueOf(128);
        System.out.println(var1 == var2);
        System.out.println(var3 == var4);
        System.out.println(var5 == var6);
        System.out.println(var7 == var8);
    }
}
