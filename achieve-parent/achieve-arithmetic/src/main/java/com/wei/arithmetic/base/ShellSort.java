package com.wei.arithmetic.base;

import com.wei.arithmetic.sort.abs.AbstractSort;

import java.util.Comparator;

/**
 * @Describe 希尔排序
 * @Author wei.peng
 * @Date 2021年05月17日
 */
public class ShellSort extends AbstractSort{
    @Override
    public int[] sort(int[] array, int order) {
        if (array == null || array.length == 0) {
            return array;
        }
        return new int[0];
    }

    @Override
    public <T extends Comparable> T[] sort(T[] array, int order) {
        return null;
    }

    @Override
    public <T> T[] sort(T[] array, int order, Comparator<T> comparator) {
        return null;
    }
}
