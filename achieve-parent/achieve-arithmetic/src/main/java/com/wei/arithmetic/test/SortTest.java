package com.wei.arithmetic.test;

import com.alibaba.fastjson.JSON;
import com.wei.arithmetic.base.SelectSort;
import com.wei.arithmetic.sort.Sort;
import com.wei.utils.ArithmeticUtils;

import java.util.Comparator;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年05月05日
 */
public class SortTest implements Comparable{
    public static void main(String[] args) {
        Integer[] array = ArithmeticUtils.getRandomIntegerArray(20, 1, 100);
        System.out.println(JSON.toJSON(array));
        //测试选择排序
        Sort sort = null;
        //选择排序
        sort = new SelectSort();
        sort.sort(array, -1, Comparator.comparingInt(t -> t));
        System.out.println(JSON.toJSON(array));
        //冒泡排序
        /*sort = new BubbleSort();
        sort.sort(arr, 1);*/
        //插入排序
       /* sort = new InsertSort();
        sort.sort(arr, 1);*/
       getT(new SortTest[]{new SortTest()});
    }


    @Override
    public int compareTo(Object o) {
        return 0;
    }

    public static <T extends Comparable> T[] getT(T[] t){
        return t;
    }
}
