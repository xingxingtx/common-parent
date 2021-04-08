package com.wei.designmodel.construction.flyweight.general;

/**
 * @Describe ConcreteFlyweight
 * @Author a_pen
 * @Date 2020年09月07日
 */
public class ConcreteFlyweight implements IFlyweight{
    private String intrinsicState;

    public ConcreteFlyweight(String intrinsicState) {
        this.intrinsicState = intrinsicState;
    }

    @Override
    public void operation(String extrinsicState) {
        System.out.println("Object address: "+ System.identityHashCode(this));
        System.out.println("IntrinsicState: " + this.intrinsicState);
        System.out.println("ExtrinsicState: " + extrinsicState);
    }
}
