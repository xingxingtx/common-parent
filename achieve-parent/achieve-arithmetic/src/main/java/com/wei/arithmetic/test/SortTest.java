package com.wei.arithmetic.test;

import com.wei.arithmetic.base.SelectSort;
import com.wei.arithmetic.sort.Sort;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年05月05日
 */
public class SortTest implements Comparable{
    public static void main(String[] args) {
        int[] arr = new int[] { 5, 3, 6, 2, 10, 2, 1, 3, 5, 8, 10 , 16, 4};
        //测试选择排序
        Sort sort = null;
        //选择排序
        sort = new SelectSort();
        sort.sort(arr, -1);
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
