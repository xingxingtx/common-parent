package com.wei.utils.utils;

import java.io.InputStream;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年01月15日
 */
public class SortUtil {

    public static Integer[] sort(Integer[] list) {
        if(list == null || list.length == 0){
            return list;
        }
        int len = list.length - 1;
        /**记录最后一次交换的位置*/
        int tempPosition = 0;
        // 要遍历的次数
        for (int i = 0; i < list.length - 1; i++) {
            /**设置一个标志位*/
            int flag = 1;
            /**依次的比较相邻两个数的大小，遍历一次后，把数组中第i小的数放在第i个位置上*/
            for (int j = 0; j < len; j++) {
                /**比较相邻的元素，如果前面的数小于后面的数，交换*/
                if (list[j] > list[j + 1]) {
                    list[j] = list[j]^list[j + 1];
                    list[j + 1] = list[j]^list[j + 1];
                    list[j] = list[j]^list[j + 1];
                    /**发生交换，标志位置0*/
                    flag = 0;
                    /**记录交换的位置*/
                    tempPosition = j;
                }
            }
            /**把最后一次交换的位置给len，来缩减内循环的次数*/
            len = tempPosition;
            /**如果没有交换过元素，则已经有序*/
            if (flag == 1) {
                break;
            }
        }
        return list;
    }

    public static int compareToTwoArray(Integer[] head, Integer[] tail){
        sort(head);
        sort(tail);
        if(head.length < tail.length){
            for (int i = 0; i < head.length; i++) {
                if(head[i] < tail[i]){
                    return -1;
                }else if(head[i] > tail[i]){
                    return 1;
                }
            }
            return -1;
        }else if(head.length == tail.length){
            for (int i = 0; i < tail.length; i++) {
                if(head[i] < tail[i]){
                    return -1;
                }else if(head[i] > tail[i]){
                    return 1;
                }
            }
            return 0;
        }else {
            for (int i = 0; i < tail.length; i++) {
                if(head[i] < tail[i]){
                    return -1;
                }else if(head[i] > tail[i]){
                    return 1;
                }
            }
            return 1;
        }
    }
}
