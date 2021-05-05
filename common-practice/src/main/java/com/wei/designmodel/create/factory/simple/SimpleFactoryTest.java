package com.wei.designmodel.create.factory.simple;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年04月08日
 */
public class SimpleFactoryTest {
    public static void main(String[] args) throws Exception {
        SimpleFactory.getInstance().createCourseV1("java").study();
        SimpleFactory.getInstance().createCourseV2(JavaCourse.class.getName()).study();
        SimpleFactory.getInstance().createCourseV3(PythonCourse.class).study();
    }

}
