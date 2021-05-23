package com.wei.arithmetic.sort;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年05月05日
 */
public interface Sort {

    /**
     * 排序int数组
     * @param array 排序数组
     * @param order -1 降序，其余升序
     */
     public int[] sort(int[] array, int order);

    /**
     * 排序对象数组
     * @param array 排序数组
     * @param order -1 降序，其余升序
     */
    public <T extends Comparable> T[] sort(T[] array, int order);

}
