package com.wei.designmodel.create.singleton;

import com.wei.designmodel.create.singleton.lazy.LazyInnerClassSingleton;

import java.lang.reflect.Constructor;

/**
 * @author wei.peng
 * @descripe
 * @Date 2019/8/23 0023.
 */
public class SingletonTest {

    public static void main(String[] args) {
        Class<LazyInnerClassSingleton> singletonClass = LazyInnerClassSingleton.class;
        /**反射获取实例*/
        try {
            Constructor<LazyInnerClassSingleton> constructor = singletonClass.getDeclaredConstructor(null);
            constructor.setAccessible(true);
            LazyInnerClassSingleton objectA = constructor.newInstance();
            LazyInnerClassSingleton objectB = constructor.newInstance();
            System.out.println(objectA);
            System.out.println(objectA == LazyInnerClassSingleton.getInstance());
            LazyInnerClassSingleton objectc = constructor.newInstance();
            System.out.println(objectA == objectB);

        } catch (Exception e) {

        }
    }

}
