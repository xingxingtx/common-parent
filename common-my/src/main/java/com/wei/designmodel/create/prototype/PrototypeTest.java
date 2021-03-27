package com.wei.designmodel.create.prototype;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Describe PrototypeTest
 * @Author a_pen
 * @Date 2020年08月10日
 */
public class PrototypeTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        Student student = new Student();
        student.setAge(12);
        student.setName("test");
        List<String> list = new ArrayList<>(Arrays.asList("1","2","3"));
        student.setList(list);
        System.out.println(student.toString());
        /**浅克隆*/
        Student clone = (Student)student.clone();
        clone.setName("test1");
        list.add("4");
        clone.setList(list);
        System.out.println(clone.toString());
        System.out.println(student.toString());


    }
}
