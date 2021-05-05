package com.wei.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @Describe 公共基础配置类
 * @Author wei.peng
 * @Date 2021年04月01日
 */
@Configuration
@ConfigurationProperties(prefix = "common.properties")
public class CommonProperties {

    private AuthenticCommonConfig authentic;

    private GlobalCommonConfig global;

    public AuthenticCommonConfig getAuthentic() {
        return authentic;
    }

    public void setAuthentic(AuthenticCommonConfig authentic) {
        this.authentic = authentic;
    }

    public GlobalCommonConfig getGlobal() {
        return global;
    }

    public void setGlobal(GlobalCommonConfig global) {
        this.global = global;
    }

    /**权限验证公共配置类*/
    public static class AuthenticCommonConfig{
        /**是否开启spring security 权限验证*/
        private boolean enableSecurity = false;
        /**是否开启Shiro 权限验证*/
        private boolean enableShiro = false;
        /**忽略资源url,配置以逗号隔开*/
        private Map<String, String> Ignoring;

        public Map<String, String> getIgnoring() {
            return Ignoring;
        }

        public void setIgnoring(Map<String, String> ignoring) {
            Ignoring = ignoring;
        }

        public boolean isEnableSecurity() {
            return enableSecurity;
        }

        public void setEnableSecurity(boolean enableSecurity) {
            this.enableSecurity = enableSecurity;
        }

        public boolean isEnableShiro() {
            return enableShiro;
        }

        public void setEnableShiro(boolean enableShiro) {
            this.enableShiro = enableShiro;
        }
    }

    public static class GlobalCommonConfig{
        /**是否开启异常处理*/
        private String exception;

        public String getException() {
            return exception;
        }

        public void setException(String exception) {
            this.exception = exception;
        }
    }
}
