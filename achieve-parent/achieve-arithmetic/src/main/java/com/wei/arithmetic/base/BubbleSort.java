package com.wei.arithmetic.base;

import com.wei.arithmetic.sort.abs.AbstractSort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Describe 冒泡排序
 * @Author wei.peng
 * @Date 2021年05月05日
 */
public class BubbleSort extends AbstractSort {

    private static Logger log = LoggerFactory.getLogger(BubbleSort.class);

    /**
     *
     * @param array 排序数组
     * @param order -1 降序，其余升序
     * 原理：依次比较相邻的两个数,将小/大数学放在前面,大/小数数放在后面。
     * 2)比较步骤：
     * 设数组的长度为N：
     * (1)比较前后相邻的二个数据，如果前面数据大于后面的数据，就将这二个数据交换。
     * (2)这样对数组的第0个数据到N-1个数据进行一次遍历后，最大的一个数据就“沉”到数组第N-1个位置。
     * (3)N=N-1，如果N不为0就重复前面二步，否则排序完成。
     *
     */
    @Override
    public int[] sort(int[] array, int order) {
        if (array == null || array.length == 0) {
            return array;
        }
        //比较次数
        int count = 0;
        int j, k;
        //记录最后交换的位置，也就是排序的尾边界
        int flag = array.length - 1;
        while (flag > 0) {
            k = flag;
            flag = 0;
            for (j = 0; j < k; j++) {
                count++;
                log.debug("比较次数为:{}", count);
                //降序排列
                if (order == -1) {
                    if (array[j] < array[j + 1]) {
                        swap(array, j, j + 1);
                        //记录交换的末端位置
                        flag = j;
                        log.debug(Arrays.toString(array));
                    }
                } else {
                    if (array[j] > array[j + 1]) {
                        swap(array, j, j + 1);
                        //记录交换的末端位置
                        flag = j;
                        log.debug(Arrays.toString(array));
                    }
                }
            }
        }
        return array;
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
