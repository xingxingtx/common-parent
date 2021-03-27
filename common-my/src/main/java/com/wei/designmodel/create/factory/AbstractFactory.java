package com.wei.designmodel.create.factory;

/**
 * @author wei.peng
 * @descripe
 * @Date 2019/8/26 0026.
 */
public abstract class AbstractFactory<T> implements Factory<T>{

    protected boolean isSingleton = true;

    private T instance;

    public boolean isSingleton() {
        return this.isSingleton;
    }

    public void setSingleton(boolean singleton) {
        this.isSingleton = singleton;
    }

    @Override
    public T getInstance() {
        Object var1;
        if(isSingleton){
            if(null == this.instance){
                this.instance = this.createInstance();
            }
            var1 = this.instance;
        }else {
            var1 = this.createInstance();
        }
        if(var1 == null) {
            String msg = "Factory 'createInstance' implementation returned a null object.";
            throw new IllegalStateException(msg);
        }
        return (T) var1;
    }


    /**
     * 创建实例对象
     * @return
     */
    protected abstract T createInstance();
}
