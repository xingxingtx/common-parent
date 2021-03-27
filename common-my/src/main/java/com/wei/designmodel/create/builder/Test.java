package com.wei.designmodel.create.builder;

/**
 * @author wei.peng
 * @descripe
 * @Date 2019/8/26 0026.
 */
public class Test {

    public static void main(String[] args) {
        Computer computer = new Computer.Builder()
                    .setCpu("cpu")
                    .setMainboard("mainboard")
                    .setRam("ram")
                    .create();
        System.out.println(computer.toString());
    }
}
