package com.wei.arithmetic.sort.abs;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年05月05日
 */
public interface Sort<T> {

    /**
     * 排序int数组
     *
     * @param array 排序数组
     * @param order -1 降序，其余升序
     */
    int[] sort(int[] array, int order);

    /**
     * 排序对象数组
     *
     * @param array 排序数组
     * @param order -1 降序，其余升序
     */
    T[] sort(T[] array, int order);


}
