package com.wei.lambda;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Describe 语法格式：(parameters) -> expression或(parameters) ->{ statements; }
 * @Author wei.peng
 * @Date 2021年05月23日
 */
public class LambdaDemo {

    private static List<People> ints = new ArrayList<>();

    static {
        ints.add(new People("wei", 1));
        ints.add(new People("wei1", 4));
        ints.add(new People("wei2", 5));
        ints.add(new People("wei3", 3));
    }

    public static void main(String[] args) {
        GreetingService greetService1 = message -> "ss";
        System.out.println(greetService1.sayMessage("111"));
        List<People> i = ints.parallelStream().filter(t -> t.getAge() > 3).collect(Collectors.toList());
        System.out.println(JSON.toJSON(ints));
        System.out.println(JSON.toJSON(i));
       /* LambdaDemo demo = new LambdaDemo();
        demo.init();
        //调用实例方法
        //等效于ints.sort(null);
        ints.sort(People::compares);
        System.out.println(ints);*/
    }


    public static class People implements Comparator<People>, Comparable<People> {

        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public People(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public People() {
        }

        @Override
        public int compareTo(People o) {
            if (o.getAge() == getAge()) {
                return 0;
            } else if (o.getAge() > getAge()) {
                return 1;
            }
            return -1;
        }

        @Override
        public int compare(People o1, People o2) {
            if (o1.getAge() == o2.getAge()) {
                return 0;
            } else if (o1.getAge() > o2.getAge()) {
                return 1;
            }
            return -1;
        }

        public static int compares(People o1, People o2) {
            if (o1.getAge() == o2.getAge()) {
                return 0;
            } else if (o1.getAge() > o2.getAge()) {
                return -1;
            }
            return 1;
        }

        @Override
        public String toString() {
            return "People{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }


}
