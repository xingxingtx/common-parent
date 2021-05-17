package com.wei.myspring.beans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年04月13日
 */
public interface BeanFactory {

    /**
     * 根据bean名称获取bean实例
     * @param name
     * @return
     * @throws BeansException
     */
    Object getBean(String name) throws BeansException;

    /**
     * 根据bean名称获取指定class 类型的bean实例
     * @param name
     * @param requiredType
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T> T getBean(String name, Class<T> requiredType) throws BeansException;

    /**
     * 根据bean名称和指定参数列数获取对应的bean实例
     * @param name
     * @param args
     * @return
     * @throws BeansException
     */
    Object getBean(String name, Object... args) throws BeansException;

    /**
     * 根据class类型获取bean实例
     * @param requiredType
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T> T getBean(Class<T> requiredType) throws BeansException;


    <T> T getBean(Class<T> requiredType, Object... args) throws BeansException;

    /**
     * 判断是否包含name的对应实例
     * @param name
     * @return
     */
    boolean containsBean(String name);

    /**
     * 判断是否事单例
     * @param name
     * @return
     * @throws NoSuchBeanDefinitionException
     */
    boolean isSingleton(String name) throws NoSuchBeanDefinitionException;

    /**
     * Return the aliases for the given bean name, if any.
     * <p>All of those aliases point to the same bean when used in a {@link #getBean} call.
     * <p>If the given name is an alias, the corresponding original bean name
     * and other aliases (if any) will be returned, with the original bean name
     * being the first element in the array.
     * <p>Will ask the parent factory if the bean cannot be found in this factory instance.
     * @param name the bean name to check for aliases
     * @return the aliases, or an empty array if none
     * @see #getBean
     */
    String[] getAliases(String name);
}
