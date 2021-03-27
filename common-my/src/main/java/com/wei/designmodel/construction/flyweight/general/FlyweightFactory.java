package com.wei.designmodel.construction.flyweight.general;

import java.util.HashMap;
import java.util.Map;

/**
 * @Describe 享元工厂
 * @Author a_pen
 * @Date 2020年09月07日
 */
public class FlyweightFactory {

    private static Map<String, IFlyweight> pool = new HashMap<>();


    public static IFlyweight getFlyweight(String intrinsicState){
        synchronized (pool) {
            if(!pool.containsKey(intrinsicState)){
                IFlyweight flyweight = new ConcreteFlyweight(intrinsicState);
                pool.put(intrinsicState, flyweight);
                return flyweight;
            }
        }
        return pool.get(intrinsicState);
    }


}
