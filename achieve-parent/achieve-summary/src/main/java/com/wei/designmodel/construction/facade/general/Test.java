package com.wei.designmodel.construction.facade.general;

class Test {
    // 客户
    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.doA();
        facade.doB();
        facade.doC();
    }
}