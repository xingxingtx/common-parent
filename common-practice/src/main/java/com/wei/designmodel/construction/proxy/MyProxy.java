package com.wei.designmodel.construction.proxy;


/**
 * @author wei.peng
 * @describe MyProxy
 * @date 2020 05/09
 */
public class MyProxy {
    private static final String ln = "\r\n";

    public static Object newProxyInstance(ClassLoader classLoader, Class<?>[] interfaces, MyInvocationHandler handler) {
        /**生成java文件*/
        generateJavaFile(interfaces);
        return null;
    }

    private static void generateJavaFile(Class<?>[] interfaces) {
        StringBuilder sb = new StringBuilder();
        sb.append(MyProxy.class.getPackage() + ln);

    }
}
