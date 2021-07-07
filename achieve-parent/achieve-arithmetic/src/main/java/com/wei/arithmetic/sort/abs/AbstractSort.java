package com.wei.arithmetic.sort.abs;

import com.wei.arithmetic.sort.Sort;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年05月06日
 */
public abstract class AbstractSort implements Sort {

    /**
     * 数组两个元素值交换
     *
     * @param arr
     * @param i
     * @param j
     */
    protected void swap(int[] arr, int i, int j) {
        if (arr == null || arr.length == 0 || i == j) {
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }


    /**
     * 数组两个元素值交换
     *
     * @param arr
     * @param i
     * @param j
     */
    protected <T> void swap(T[] arr, int i, int j) {
        if (arr == null || arr.length == 0 || i == j) {
            return;
        }
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


}
