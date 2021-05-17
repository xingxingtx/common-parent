package com.wei.designmodel.construction.decorator.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * @Author wei.peng
 */
public class JsonLoggerFactory {

    public static JsonLogger getLogger(Class clazz){
        Logger logger = LoggerFactory.getLogger(clazz);
        return new JsonLogger(logger);
    }
}
