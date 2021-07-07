package com.wei.utils;

import com.alibaba.fastjson.JSON;

import java.util.Random;

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

    /**
     * 获取固定大小范围内指定长度的随机数组
     * @param length
     */
    public static int[] getRandomIntArrays(int length, int start, int end){
        if(length <= 0 || start >= end){
            return new int[]{};
        }
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = start + new Random().nextInt(end);
        }
        return array;
    }

    /**
     * 获取固定大小范围内指定长度的随机数组
     * @param length
     */
    public static Integer[] getRandomIntegerArray(int length, int start, int end){
        if(length <= 0 || start >= end){
            return new Integer[]{};
        }
        Integer[] array = new Integer[length];
        for (int i = 0; i < length; i++) {
            array[i] = start + new Random().nextInt(end);
        }
        return array;
    }

    public static void main(String[] args) {
        Integer[] array = getRandomIntegerArray(12, 1, 100);
        System.out.println(JSON.toJSON(array));
    }

}

