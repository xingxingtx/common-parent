package com.wei.arithmetic.sort;

import com.wei.arithmetic.sort.abs.AbstractSort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Comparator;

/**
 * @Describe 冒泡排序
 * 原理：依次比较相邻的两个数,将小/大数学放在前面,大/小数数放在后面。
 * 2)比较步骤：
 * 设数组的长度为N：
 * (1)比较前后相邻的二个数据，如果前面数据大于后面的数据，就将这二个数据交换。
 * (2)这样对数组的第0个数据到N-1个数据进行一次遍历后，最大的一个数据就“沉”到数组第N-1个位置。
 * (3)N=N-1，如果N不为0就重复前面二步，否则排序完成。
 * @Author wei.peng
 * @Date 2021年05月05日
 */
public class BubbleSort<T> extends AbstractSort<T> {

    private static Logger log = LoggerFactory.getLogger(BubbleSort.class);

    public BubbleSort(Comparator<T> comparator) {
        super(comparator);
    }

    public BubbleSort() {
    }

    /**
     * @param array 排序数组
     * @param order -1 降序，其余升序
     */
    @Override
    public int[] sort(int[] array, int order) {
        if (array == null || array.length == 0) {
            return array;
        }
        boolean isSwap;
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            isSwap = false;
            for (int j = 0; j < array.length - i - 1; j++) {
                count++;
                //降序排序
                if (order == -1) {
                    if (array[j] - array[j + 1] < 0) {
                        isSwap = true;
                        swap(array, j, j + 1);
                    }
                } else {
                    //升序排序
                    if (array[j] - array[j + 1] > 0) {
                        isSwap = true;
                        swap(array, j, j + 1);
                    }
                }
                //log.info("第 {} 次排序后array:{}", count, JSON.toJSON(array));
            }
            //未交换位置，说明已经是有序数组
            if (!isSwap) {
                break;
            }
        }
        return array;
    }


    @Override
    public T[] sort(T[] array, int order) {
        if (array == null || array.length == 0) {
            return array;
        }
        boolean isSwap;
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            isSwap = false;
            for (int j = 0; j < array.length - i - 1; j++) {
                count++;
                boolean bl = (comparator != null && comparator.compare(array[j], array[j]) < 0)
                        || ((Comparable & Serializable) array[j]).compareTo(array[j + 1]) < 0;
                //降序排序
                if (order == -1) {
                    if (bl) {
                        isSwap = true;
                        swap(array, j, j + 1);
                    }
                } else {
                    //升序排序
                    if (!bl) {
                        isSwap = true;
                        swap(array, j, j + 1);
                    }
                }
                //log.info("第 {} 次排序后array:{}", count, JSON.toJSON(array));
            }
            //未交换位置，说明已经是有序数组
            if (!isSwap) {
                break;
            }
        }
        return array;
    }
}
