package com.wei.arithmetic.test;

import com.alibaba.fastjson.JSON;
import com.wei.arithmetic.sort.ShellSort;
import com.wei.arithmetic.sort.abs.Sort;
import com.wei.utils.ArithmeticUtils;

import java.util.Comparator;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年05月05日
 */
public class SortTest {

    public static void main(String[] args) {
        Integer[] array1 = ArithmeticUtils.getRandomIntegerArray(20, 1, 1000);
        int[] array2 = ArithmeticUtils.getRandomIntArrays(11, 1, 1000);
        System.out.println(JSON.toJSON(array1));
        //测试选择排序
        Sort sort;
        long start = System.currentTimeMillis();
        //1.选择排序
        //sort = new SelectSort(Comparator.comparingInt(o -> (int) o));

        //2.冒泡排序
        //sort = new BubbleSort(Comparator.comparingInt(o -> (int) o));

        //3.插入排序
        //sort = new InsertSort(Comparator.comparingInt(o -> (int) o));

        //4.快速排序
        //sort = new QuickSort(Comparator.comparingInt(o -> (int) o));

        //5.归并排序
        //sort = new MergeSort(Comparator.comparingInt(o -> (int) o));

        //6.希尔排序
        sort = new ShellSort(Comparator.comparingInt(o -> (int) o));
        sort.sort(array1, 1);

        //7.桶排序
        long end = System.currentTimeMillis();
        System.out.println("-------waste time:" + (end - start));
        System.out.println(JSON.toJSON(array1));
    }


}
