package com.wei.i18n;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年05月27日
 */
public class SpringMessageSource extends ResourceBundleMessageSource {

    private static final String DEFAULT_MESSAGES = "i18n/messages";
    private String baseName;

    public SpringMessageSource(String baseName) {
        this.baseName = baseName;
    }

    public static MessageSourceAccessor getAccessor(){
        return new MessageSourceAccessor(new SpringMessageSource(DEFAULT_MESSAGES));
    }

    public String getBaseName() {
        return baseName;
    }

    public void setBaseName(String baseName) {
        this.baseName = baseName;
    }
}
