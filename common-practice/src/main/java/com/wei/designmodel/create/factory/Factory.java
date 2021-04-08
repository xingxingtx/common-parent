package com.wei.designmodel.create.factory;

/**
 *
 * @author wei.peng
 * @descripe 工厂角色-creator
 * @Date 2019/8/26 0026.
 */
public interface Factory<T> {

    /**
     *  返回T实例对象
     * @return T
     */
    T getInstance();


}
