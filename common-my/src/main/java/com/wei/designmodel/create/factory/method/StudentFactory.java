package com.wei.designmodel.create.factory.method;


import com.wei.designmodel.create.factory.AbstractFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wei.peng
 * @descripe
 * @Date 2019/8/26 0026.
 */
public class StudentFactory extends AbstractFactory<FactoryMethod> {
    private Logger log = LoggerFactory.getLogger(StudentFactory.class);
    private static Student student = new Student();


    @Override
    protected FactoryMethod createInstance() {
        if(isSingleton()){
            log.info("create singleton instance ");
            return student;
        }
        return new Student();
    }
}
