package com.wei.config.security.service;

import org.springframework.security.config.annotation.web.builders.WebSecurity;

import java.util.Map;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年04月02日
 */
public interface IAuthorityAndSecurityService {

    /**
     * 获取忽略资源
     * @return map key:对应url，value：方法类型
     * @param web
     */
    public void setWebSecurity(WebSecurity web);

}
