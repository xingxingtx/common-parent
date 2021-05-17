package com.wei.designmodel.create.factory.method;


import com.wei.designmodel.create.factory.AbstractFactory;

/**
 * @author wei.peng
 * @descripe
 * @Date 2019/8/26 0026.
 */
public class TeacherFactory  extends AbstractFactory<FactoryMethod> {

    @Override
    protected FactoryMethod createInstance() {
        return new Teacher();
    }
}
