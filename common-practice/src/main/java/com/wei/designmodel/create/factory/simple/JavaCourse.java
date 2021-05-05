package com.wei.designmodel.create.factory.simple;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年04月08日
 */
public class JavaCourse implements ICourse{
    @Override
    public void study() {
        /**doing somethings here*/
        System.out.println("学习Java课程");
    }
}
