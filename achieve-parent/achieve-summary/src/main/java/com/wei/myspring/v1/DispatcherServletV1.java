package com.wei.myspring.v1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Describe DispatcherServletV1
 * @Author a_pen
 * @Date 2020年09月08日
 */
public class DispatcherServletV1 extends HttpServlet {

    private static final Logger log  = LoggerFactory.getLogger(DispatcherServletV1.class);

    private static Properties properties = new Properties();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            doDispatch();
    }

    private void doDispatch() {
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        /**1、加载配置文件*/
        doLoadConfig(config);
        /**2、扫描初始化容器*/
        doScanner();
        /**3、完成ioc*/
        doInstance();
        /**4、完成di*/
        doDi();
        /**5、完handlerMapping映射*/
        doInitHandlerMapping();
    }

    private void doDi() {
    }

    private void doInitHandlerMapping() {
    }

    private void doInstance() {
    }

    private void doScanner() {
        properties.get("myspring.scanner.packpage");
    }

    private void doLoadConfig(ServletConfig config) {
        String contextPath = config.getServletContext().getContextPath();
        InputStream resource = this.getClass().getClassLoader().getResourceAsStream(contextPath + "/mySpring.properties");
        try {
            properties.load(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
