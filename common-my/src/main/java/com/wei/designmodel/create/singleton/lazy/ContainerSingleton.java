package com.wei.designmodel.create.singleton.lazy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Describe ContainerSingleton
 * @Author a_pen
 * @Date 2020年08月10日
 */
public class ContainerSingleton {

    private static Map<String, Object>  objectMap = new ConcurrentHashMap<>();

    private ContainerSingleton() {
    }

    public static Object getBean(String className){
        synchronized (objectMap){
            if (objectMap.containsKey(className)){
                return objectMap.get(className);
            }else {
                Object instance = null;
                try {
                     instance = Class.forName(className).newInstance();
                     objectMap.put(className, instance);
                } catch (Exception e) {

                }
                return instance;
            }
        }
    }
}
