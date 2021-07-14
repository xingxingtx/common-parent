package com.wei.arithmetic.sort;

import com.wei.arithmetic.sort.abs.AbstractSort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Comparator;

/**
 * @Describe  归并排序
 * @Author wei.peng
 * @Date 2021年07月09日
 */
public class MergeSort<T> extends AbstractSort<T> {
    private static final Logger log = LoggerFactory.getLogger(MergeSort.class);

    public MergeSort(Comparator<T> comparator) {
        super(comparator);
    }
    public MergeSort() {
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
        mergeSort(array, 0, array.length - 1, order);
        return array;
    }

    private void mergeSort(int[] array, int left, int right, int order) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) >> 1;
        mergeSort(array, left, mid, order);
        mergeSort(array, mid + 1, right, order);
        merge(array, left, mid, right, order);
        //log.info("array:{}", JSON.toJSON(array));
    }

    private  void merge(int[] array, int left, int mid, int right, int order) {
        int[] temp = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= right) {
            //降序排序
            if (order == -1) {
                if (array[i] > array[j]) {
                    temp[k++] = array[i++];
                } else {
                    temp[k++] = array[j++];
                }
            } else {
                if (array[i] < array[j]) {
                    temp[k++] = array[i++];
                } else {
                    temp[k++] = array[j++];
                }
            }
        }
        //
        while (i <= mid) {
            temp[k++] = array[i++];
        }
        while (j <= right) {
            temp[k++] = array[j++];
        }
        System.arraycopy(temp, 0, array, left, temp.length);
        temp = null;
    }

    @Override
    public T[] sort(T[] array, int order) {
        if (array == null || array.length <= 1) {
            return array;
        }
        mergeSort(array, 0, array.length - 1, order);
        return array;
    }

    private void mergeSort(T[] array, int left, int right, int order) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) >> 1;
        mergeSort(array, left, mid, order);
        mergeSort(array, mid + 1, right, order);
        merge(array, left, mid, right, order);
        //log.info("array:{}", JSON.toJSON(array));
    }

    private  void merge(T[] array, int left, int mid, int right, int order) {
        Object[] temp = new Object[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= right) {
            boolean bl = (comparator != null && comparator.compare(array[i], array[j]) > 0)
                    || (((Comparable<T> & Serializable)array[i]).compareTo(array[j]) > 0);
            //降序排序
            if (order == -1) {
                if (bl) {
                    temp[k++] = array[i++];
                } else {
                    temp[k++] = array[j++];
                }
            } else {
                if (!bl) {
                    temp[k++] = array[i++];
                } else {
                    temp[k++] = array[j++];
                }
            }
        }
        //
        while (i <= mid) {
            temp[k++] = array[i++];
        }
        while (j <= right) {
            temp[k++] = array[j++];
        }
        System.arraycopy(temp, 0, array, left, temp.length);
        temp = null;
    }
}
