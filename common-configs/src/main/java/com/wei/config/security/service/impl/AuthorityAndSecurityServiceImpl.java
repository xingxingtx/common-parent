package com.wei.config.security.service.impl;

import com.wei.config.security.service.IAuthorityAndSecurityService;
import com.wei.properties.CommonProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年04月02日
*/
@Service
public class AuthorityAndSecurityServiceImpl implements IAuthorityAndSecurityService {
    private static final Logger log = LoggerFactory.getLogger(AuthorityAndSecurityServiceImpl.class);
    private static final Map<String, String> DEFAULT_IGNORING_SOURCE = new HashMap<>();
    static {
        DEFAULT_IGNORING_SOURCE.put("/js/**", null);
        DEFAULT_IGNORING_SOURCE.put("/css/**", null);
        DEFAULT_IGNORING_SOURCE.put("/images/**", null);
    }
    @Autowired
    private CommonProperties commonProperties;

    @Override
    public void setWebSecurity(WebSecurity web) {
        if(commonProperties.getAuthentic().getIgnoring() != null && commonProperties.getAuthentic().getIgnoring().size() > 0 ){
            log.info("get ignoring source from commonProperties");
            setWebSecurity(web, commonProperties.getAuthentic().getIgnoring());
        }
        setWebSecurity(web, DEFAULT_IGNORING_SOURCE);
    }


    private static void setWebSecurity(WebSecurity web, Map<String, String> ignoring){
        Iterator<Map.Entry<String, String>> iterator = ignoring.entrySet().iterator();
        while (iterator.hasNext()){
            String key = iterator.next().getKey();
            String value = iterator.next().getValue();
            HttpMethod resolve = HttpMethod.resolve(value);
            WebSecurity.IgnoredRequestConfigurer configurer = web.ignoring();
            if(resolve != null){
                configurer.mvcMatchers(resolve, key);
            }else {
                configurer.mvcMatchers(key);
            }
        }
    }
}
