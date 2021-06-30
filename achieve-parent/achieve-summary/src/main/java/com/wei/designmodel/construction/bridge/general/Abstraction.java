package com.wei.designmodel.construction.bridge.general;

// 抽象
public abstract class Abstraction {

    protected IImplementor mImplementor;

    public Abstraction(IImplementor implementor) {
        this.mImplementor = implementor;
    }

    public void operation() {
        this.mImplementor.operationImpl();
    }
}