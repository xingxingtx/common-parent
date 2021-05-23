package com.wei.base.reflect;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年04月17日
 */
public class ReflectTest {
    private String name;
    private String address;


    public static void main(String[] args) {
        Class<ReflectTest> aClass = ReflectTest.class;
        System.out.println(aClass.getTypeName());
    }


    public String getResult(){
        return this.name + this.address;
    }
    public ReflectTest(String name) {
        this.name = name;
    }

    public ReflectTest(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public ReflectTest() {
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "ReflectTest{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
