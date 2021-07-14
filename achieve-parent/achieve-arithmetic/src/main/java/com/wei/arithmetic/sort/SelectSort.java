package com.wei.arithmetic.sort;

import com.alibaba.fastjson.JSON;
import com.wei.arithmetic.sort.abs.AbstractSort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Comparator;

/**
 * @Describe 选择排序
 * 原理: 每一趟从待排序的记录中选出最小的元素，顺序放在已排好序的序列最后，直到全部记录排序完毕
 * 思路:
 * 1. 待排序数组: int[] array = { 5, 3, 6, 2, 10, 2, 1, 9,44,19};
 * 2. 第1趟排序，在待排序数据array[0]~array[n-1]中选出最小的数据，将它与array[0]交换；
 * 3. 第2趟排序，在待排序数据array[1]~array[n-1]中选出最小的数据，将它与array[1]交换；
 * 4. 以此类推，第i趟在待排序数据array[i-1]~array[n-1]中选出最小的数据，将它与array[i-1]交换，直到全部排序完成，需要n-1趟。
 * @Author wei.peng
 * @Date 2021年05月05日
 */
public class SelectSort<T> extends AbstractSort<T> {

    private static Logger log = LoggerFactory.getLogger(SelectSort.class);


    public SelectSort(Comparator<T> comparator) {
        super(comparator);
    }

    public SelectSort() {
    }

    /**
     * 选择排序
     *
     * @param array 需要排序的元素
     * @param order -1 降序 其余默认升序
     */
    @Override
    public int[] sort(int[] array, int order) {
        if (array == null || array.length == 0) {
            return array;
        }
        //比较次数
        int count = 0;
        int length = array.length - 1;
        //需要比较的次数为array.length - 1次
        for (int i = 0; i < length; i++) {
            //假设每一趟第一个元素都是最小值或者最大值，记录当前索引
            int temp = i;
            //从剩余未排序序列中找到最小值下标
            for (int j = i + 1; j < array.length; j++) {
                count++;
                //降序排序
                if (order == -1) {
                    if (array[temp] < array[j]) {
                        temp = j;
                    }
                } else {//升序排序
                    if (array[temp] > array[j]) {
                        temp = j;
                    }
                }
                log.debug("比较次数为:{}, array:{}", count, JSON.toJSON(array));
            }
            //如果本身就是最小值或者最大值下标，则不需要交换
            if (temp != i) {
                swap(array, i, temp);
            }
        }
        return array;
    }

    @Override
    public T[] sort(T[] array, int order) {
        if (array == null || array.length == 0) {
            return array;
        }
        //存放每次最大或者最小值的数组下标
        int temp;
        //比较次数
        int count = 0;
        for (int i = 0; i < array.length - 1; i++) {
            temp = i;
            for (int j = i + 1; j < array.length; j++) {
                count++;
                //降序排序
                boolean bl = (comparator != null && comparator.compare(array[temp], array[j]) < 0)
                        || ((Comparable & Serializable) array[temp]).compareTo(array[j]) < 0;
                if (order > 0) {
                    if (bl) {
                        temp = j;
                    }
                } else {
                    //升序排序
                    if (!bl) {
                        temp = j;
                    }
                }
                log.debug("比较次数为:{}, array:{}", count, JSON.toJSON(array));
            }
            if (temp != i) {
                swap(array, i, temp);
            }
        }
        return array;
    }
}
