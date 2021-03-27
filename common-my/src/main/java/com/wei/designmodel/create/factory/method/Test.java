package com.wei.designmodel.create.factory.method;


import com.wei.designmodel.create.factory.AbstractFactory;
import com.wei.designmodel.create.factory.Factory;

/**
 * @author wei.peng
 * @descripe
 * @Date 2019/8/26 0026.
 */
public class Test {
    public static void main(String[] args) {
        AbstractFactory<FactoryMethod> student = new StudentFactory();
        student.setSingleton(false);
        student.getInstance().doSomethings();
        Factory<FactoryMethod> teacher = new TeacherFactory();
        teacher.getInstance().doSomethings();
    }
}
