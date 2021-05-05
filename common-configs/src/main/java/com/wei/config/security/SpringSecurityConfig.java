package com.wei.config.security;

import com.wei.config.security.service.IAuthorityAndSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年04月02日
 */
@Configuration
@ConditionalOnProperty(prefix = "common.properties.authentic", name = "enableSecurity", havingValue = "true")
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private IAuthorityAndSecurityService  authorityAndSecurityService;
    /**用于配置权限验证*/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    /**用来配置忽略掉的 URL 地址，一般对于静态文件*/
    @Override
    public void configure(WebSecurity web) throws Exception {
        authorityAndSecurityService.setWebSecurity(web);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

    }
}
