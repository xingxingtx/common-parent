package com.wei.arithmetic.sort;

import com.wei.arithmetic.sort.abs.AbstractSort;

import java.util.Comparator;

/**
 * @Describe 桶排序
 * @Author wei.peng
 * @Date 2021年07月12日
 */
public class BucketSort<T> extends AbstractSort<T> {


    public BucketSort() {
    }

    public BucketSort(Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    public int[] sort(int[] array, int order) {
        if(array == null || array.length <= 1){
            return array;
        }

        return array;
    }

    @Override
    public T[] sort(T[] array, int order) {
        if(array == null || array.length <= 1){
            return array;
        }

        return array;
    }
}
