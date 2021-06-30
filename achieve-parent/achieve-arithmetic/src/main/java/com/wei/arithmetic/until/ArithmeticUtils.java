package com.wei.arithmetic.until;

import com.alibaba.fastjson.JSON;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年06月13日
 */
public class ArithmeticUtils {

    /**
     * 判断Object数组内是否全部是同一类型组成的数组
     * @param objects
     * @return
     */
    public static boolean isCommonType(Object[] objects){
        if(objects != null && objects.length > 0){
            String object = objects[0].getClass().getName();
            for (int i = 1; i < objects.length; i++) {
                if(!objects[i].getClass().getTypeName().equals(object)){
                    return false;
                }
            }
        }else {
            return false;
        }
        return true;
    }

    /**
     * 前缀区间和数组比如：数组[3,9,3,9,4,3]
     * @return 返回数组：[0, 3, 12, 15, 24, 28, 31]
     *
     */
    public static int[] prefixIntervalsSum(int [] array){
        if(array == null || array.length == 0){return new int[]{};}
        int[] preArray = new int[array.length + 1];
        for (int i = 0; i < array.length; i++) {
            preArray[i + 1] = preArray[i] + array[i];
        }
        return preArray;
    }

    public static void main(String[] args) {
        int[] array = new int[]{3,9,3,9,4,3};
        System.out.println(JSON.toJSON(prefixIntervalsSum(array)));
    }

}

