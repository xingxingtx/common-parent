package com.wei.arithmetic.binary;

/**
 * @Describe
 * 二分法查找，也称为折半法，是一种在有序数组中查找特定元素的搜索算法。
 * 二分法查找的思路如下：
 * （1）首先，从数组的中间元素开始搜索，如果该元素正好是目标元素，则搜索过程结束，否则执行下一步。
 * （2）如果目标元素大于/小于中间元素，则在数组大于/小于中间元素的那一半区域查找，然后重复步骤（1）的操作。
 * （3）如果某一步数组为空，则表示找不到目标元素。
 * 二分法查找的时间复杂度O(logn)
 * @Author wei.peng
 * @Date 2021年06月15日
 */
public class BinaryArithmetic {

    public static void main(String[] args) {
        //int[] array = new int[]{3,6,8,20,33,99};
        int[] array = new int[]{99, 96, 12, 10 , 7, 4, 2, 1};
        int search = binarySearch(array, 1);
        System.out.println(search);
    }

    /**
     * 给定有序不重复序列，二分查找target
     * @param array
     * @param target
     * @return
     */
    public static int binarySearch(int[] array, int target){
        if(array != null && array.length > 0){
            int start = 0;
            int end = array.length - 1;
            int mid;
            //判断数组升序降序
            boolean asc = false;
            if(array.length >= 2 && array[0] < array[1]){
                asc = true;
            }
            //start + 1 < end 保证最后剩余两个数退出循环
            while (start + 1 < end){
                mid = start + (end - start) / 2;
                if(array[mid] == target){
                    return mid;
                }else if(array[mid] > target){
                    if (asc) {
                        end = mid;
                    }else {
                        start = mid;
                    }
                }else {
                    if (asc) {
                        start = mid;
                    }else {
                        end = mid;
                    }
                }
            }
            if(array[start] == target){
                return start;
            }
            if(array[end] == target){
                return end;
            }
        }
        return -1;
    }

    /**
     * 给定有序可序列，并且是分两端段有序，二分查找target
     * 列如: [9,12,14,15,18,19,  1,2,4,5,7,8]
     * @param array
     * @param target
     * @return
     */
    public static int binarySearchMore(int[] array, int target){
        if(array != null && array.length > 0){
            int start = 0;
            int end = array.length - 1;
            int mid;
            //start + 1 < end 保证最后剩余两个数退出循环
            while (start + 1 < end){
                
            }

        }
        return -1;
    }

    /**
     *
     * 找峰值
     */

    /**
     * 在旋转有序数组中找最小值
     * 列如: [9,12,14,15,18,19,  1,2,4,5,7,8] 找1的数
     */

    /**
     * 给定一个数组L[238, 797, 123]
     * 求平均分成K组的最大值， 例如
     *
     */


}
