package com.wei.arithmetic.point;

import com.alibaba.fastjson.JSON;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年06月16日
 */
public class PointArithmetic {

    public static void main(String[] args) {
        System.out.println(JSON.toJSON(getSumIndex(new int[]{1,2,4,5,7,8,9,10}, 9)));
    }

    /**
     * 给定一个升序数组{1，2，4，5，7，8，9，10}
     * 求数组内任意两个数和为9的数组下标
     * @param array {1，2，4，5，7，8，9，10}
     * @param target 9
     * @return
     */
    public static int[] getSumIndex(int[] array, int target){
        int[] result = {-1, -1};
        if (array == null && array.length < 2){
            return result;
        }
        int start = 0;
        int end = array.length - 1;
        while (start < end){
            int sum = array[start] + array[end];
            if(sum > target){
                end --;
            }else if(sum < target){
                start++;
            }else {
                result[0] = start;
                result[1] = end;
                return result;
            }
        }
        return result;
    }

    /**
     * 给定一个数组[-1, -4, -1, -3, 0, 2, 1, 3, 4]
     * 使得任意三个数先加等于0
     */


}
