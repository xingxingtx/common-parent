package com.wei.designmodel.create.factory.simple;

import org.springframework.util.StringUtils;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年04月08日
 */
public final class SimpleFactory {

    public static final String PYTHON = "python";
    public static final String JAVA = "java";

    /**
     * v1 版本创建实例
     * @param name
     * @return
     */
    public ICourse createCourseV1(String name){
        if(StringUtils.isEmpty(name)) {
            return null;
        }
        if(JAVA.equalsIgnoreCase(name)){
            return new JavaCourse();
        }else if(PYTHON.equalsIgnoreCase(name)){
            return new PythonCourse();
        }
        return null;
    }

    /**
     * v2 版本创建实例
     * @param name
     * @return
     */
    public ICourse createCourseV2(String name) throws Exception{
        if(StringUtils.isEmpty(name)) {
            return null;
        }
        return Class.forName(name).newInstance() instanceof ICourse ? (ICourse)Class.forName(name).newInstance() : null;
    }

    /**
     * v3 版本创建实例
     * @param course
     * @return
     */
    public ICourse createCourseV3(Class<? extends  ICourse> course ) throws Exception{
        if(StringUtils.isEmpty(course)) {
            return null;
        }
        return course.newInstance();
    }

    public static SimpleFactory getInstance(){
        return SimpleFactorySingleton.singleton;
    }
    private static class SimpleFactorySingleton{
        private static SimpleFactory singleton = new SimpleFactory();
    }
}
