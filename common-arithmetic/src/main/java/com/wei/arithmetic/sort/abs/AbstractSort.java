package com.wei.arithmetic.sort.abs;

import com.wei.arithmetic.sort.Sort;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年05月06日
 */
public abstract class AbstractSort implements Sort {

    public  void swap(int[] arr, int i, int j) {
        if(arr == null || arr.length == 0 || i == j){return;}
        arr[i] = arr[i]^arr[j];
        arr[j] = arr[i]^arr[j];
        arr[i] = arr[i]^arr[j];
    }
}
