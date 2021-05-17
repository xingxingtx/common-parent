package com.wei.utils;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年05月05日
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

}
