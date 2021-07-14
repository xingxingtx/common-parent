package com.wei.arithmetic.sort;

import com.wei.arithmetic.sort.abs.AbstractSort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Comparator;

/**
 * @Describe 快速排序(Quick Sort)使用分治法策略。
 * 它的基本思想是：选择一个基准数，通过一趟排序将要排序的数据分割成独立的两部分；其中一部分的所有数据都比另外一部分的所有数据都要小。然后，
 * 再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列。
 * 快速排序流程：
 * (1) 从数列中挑出一个基准值。
 * (2) 将所有比基准值小的摆放在基准前面，所有比基准值大的摆在基准的后面(相同的数可以到任一边)；在这个分区退出之后，该基准就处于数列的中间位置。
 * (3) 递归地把"基准值前面的子数列"和"基准值后面的子数列"进行排序
 * @Author wei.peng
 * @Date 2021年05月06日
 */
public class QuickSort<T> extends AbstractSort<T> {

    private static final Logger log = LoggerFactory.getLogger(QuickSort.class);


    public QuickSort(Comparator<T> comparator) {
        super(comparator);
    }

    public QuickSort() {
    }

    /**
     * @param array 排序数组
     * @param order -1 降序，其余升序
     * @return
     */
    @Override
    public int[] sort(int[] array, int order) {
        if (array == null || array.length <= 1) {
            return array;
        }
        quickSort(array, 0, array.length - 1, order);
        return array;
    }

    private void quickSort(int[] array, int left, int right, int order) {
        if (array.length <= 1 || left >= right) {
            return;
        }
        int mid = partition(array, left, right, order);
        quickSort(array, left, mid, order);
        quickSort(array, mid + 1, right, order);
        //log.info("array:{}", JSON.toJSON(array));
    }

    private int partition(int[] array, int left, int right, int order) {
        int temp = array[left];
        boolean desc = (order == -1);
        while (left < right) {
            if (desc) {
                // 先从最右边依次和基数做比较
                while (left < right && temp >= array[right]) {
                    right--;
                }
                // 当基准数小于了 arr[right]，则填坑
                if (left < right) {
                    array[left] = array[right];
                    left++;
                }
                //在从左边依次和基数做比较
                while (left < right && temp <= array[left]) {
                    left++;
                }
                // 当基准数大于了 arr[left]，则填坑
                if (left < right) {
                    array[right] = array[left];
                    right--;
                }
            } else {
                // 先从最右边依次和基数做比较
                while (left < right && temp <= array[right]) {
                    right--;
                }
                // 当基准数大于了 arr[right]，则填坑
                if (left < right) {
                    array[left] = array[right];
                    left++;
                }
                //在从左边依次和基数做比较
                while (left < right && temp >= array[left]) {
                    left++;
                }
                // 当基准数大于了 arr[left]，则填坑
                if (left < right) {
                    array[right] = array[left];
                    right--;
                }
            }
        }
        array[left] = temp;
        return left;
    }

    @Override
    public T[] sort(T[] array, int order) {
        if (array == null || array.length <= 1) {
            return array;
        }
        quickSort(array, 0, array.length - 1, order);
        return array;
    }

    private void quickSort(T[] array, int left, int right, int order){
        if (array.length <= 1 || left >= right) {
            return;
        }
        int mid = partition(array, left, right, order);
        quickSort(array, left, mid, order);
        quickSort(array, mid + 1, right, order);
        //log.info("array:{}", JSON.toJSON(array));
    }

    private int partition(T[] array, int left, int right, int order) {
        T temp = array[left];
        boolean desc = (order == -1);
        while (left < right) {
            if (desc) {
                // 先从最右边依次和基数做比较
                while (left < right && ((comparator != null && comparator.compare(temp, array[right]) >= 0) ||
                        ((Comparable<T> & Serializable)temp).compareTo(array[right]) >= 0)) {
                    right--;
                }
                // 当基准数小于了 arr[right]，则填坑
                if (left < right) {
                    array[left] = array[right];
                    left++;
                }
                //在从左边依次和基数做比较
                while (left < right && ((comparator != null && comparator.compare(temp, array[left]) <= 0) ||
                        ((Comparable<T> & Serializable)temp).compareTo(array[left]) <= 0)) {
                    left++;
                }
                // 当基准数大于了 arr[left]，则填坑
                if (left < right) {
                    array[right] = array[left];
                    right--;
                }
            } else {
                // 先从最右边依次和基数做比较
                while (left < right && ((comparator != null && comparator.compare(temp, array[right]) <= 0) ||
                        ((Comparable<T> & Serializable)temp).compareTo(array[right]) <= 0)) {
                    right--;
                }
                // 当基准数大于了 arr[right]，则填坑
                if (left < right) {
                    array[left] = array[right];
                    left++;
                }
                //在从左边依次和基数做比较
                while (left < right && ((comparator != null && comparator.compare(temp, array[left]) >= 0) ||
                        ((Comparable<T> & Serializable)temp).compareTo(array[left]) >= 0)) {
                    left++;
                }
                // 当基准数大于了 arr[left]，则填坑
                if (left < right) {
                    array[right] = array[left];
                    right--;
                }
            }
        }
        array[left] = temp;
        return left;
    }
}
