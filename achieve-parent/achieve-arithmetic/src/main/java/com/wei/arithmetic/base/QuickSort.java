package com.wei.arithmetic.base;

import com.wei.arithmetic.sort.abs.AbstractSort;

import java.util.Comparator;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年05月06日
 */
public class QuickSort extends AbstractSort {
    public static void main(String[] args) {
    }

    /**
     *
     * @param array 排序数组
     * @param order -1 降序，其余升序
     * @return
     */
    @Override
    public int[] sort(int[] array, int order) {

        return array;
    }

    @Override
    public <T extends Comparable> T[] sort(T[] array, int order) {
        return null;
    }



    private static int partition(int[] arr, int left, int right) {
        int temp = arr[left];
        while (right > left) {
            // 先判断基准数和后面的数依次比较
            while (temp <= arr[right] && left < right) {
                --right;
            }
            // 当基准数大于了 arr[right]，则填坑
            if (left < right) {
                arr[left] = arr[right];
                ++left;
            }
            // 现在是 arr[right] 需要填坑了
            while (temp >= arr[left] && left < right) {
                ++left;
            }
            if (left < right) {
                arr[right] = arr[left];
                --right;
            }
        }
        arr[left] = temp;
        return left;
    }

    private static void quickSort(int[] arr, int left, int right) {
        if (arr == null || left >= right || arr.length <= 1)
            return;
        int mid = partition(arr, left, right);
        quickSort(arr, left, mid);
        quickSort(arr, mid + 1, right);
    }

    @Override
    public <T> T[] sort(T[] array, int order, Comparator<T> comparator) {
        return null;
    }
}
