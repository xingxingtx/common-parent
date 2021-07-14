package com.wei.arithmetic.sort;

import com.wei.arithmetic.sort.abs.AbstractSort;

import java.io.Serializable;
import java.util.Comparator;

/**
 * @Describe 希尔排序
 * @Author wei.peng
 * @Date 2021年05月17日
 */
public class ShellSort<T> extends AbstractSort<T> {

    public ShellSort(Comparator<T> comparator) {
        super(comparator);
    }

    public ShellSort() {
    }


    @Override
    public int[] sort(int[] array, int order) {
        if (array == null || array.length <= 1) {
            return array;
        }
        //每次取步长为array.length/2
        for (int step = array.length >> 1; step > 0; step = step >> 1) {
            //根据同步长数据分成step组进行直接插入排序
            for (int j = step; j < array.length; j += step) {
                int temp = array[j];
                int k = j - step;
                int index = -1;
                //降序排序
                if (order == -1) {
                    //
                    while (k >= 0) {
                        if (temp > array[k]) {
                            //后移一位
                            array[k + step] = array[k];
                            index = k;
                        }
                        k -= step;
                    }
                } else {//升序排序
                    while (k >= 0) {
                        if (temp < array[k]) {
                            //后移一位
                            array[k + step] = array[k];
                            index = k;
                        }
                        k -= step;
                    }
                }
                //将array[j]填入index位置
                if (index != -1) {
                    array[index] = temp;
                }
            }
        }
        return array;
    }

    @Override
    public T[] sort(T[] array, int order) {
        if (array == null || array.length <= 1) {
            return array;
        }
        //每次取步长为array.length/2
        for (int step = array.length >> 1; step > 0; step = step >> 1) {
            //根据同步长数据分成step组进行直接插入排序
            for (int j = step; j < array.length; j += step) {
                //保存需要插入排序的数据
                T temp = array[j];
                int k = j - step;
                //保存tmep最终需要插入的位置
                int index = -1;
                while (k >= 0) {
                    boolean bl = (comparator != null && comparator.compare(temp, array[k]) > 0)
                            || ((Comparable & Serializable) temp).compareTo(array[k]) > 0;
                    //1、order == -1 && bl 降序排序；2、order != -1 && !bl 升序排序
                    if ((order == -1 && bl) || (order != -1 && !bl)) {
                        //后移一位
                        array[k + step] = array[k];
                        index = k;
                    }
                    k -= step;
                }
                //将array[j]填入index位置
                if (index != -1) {
                    array[index] = temp;
                }
            }
        }

        return array;
    }

}
