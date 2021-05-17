package com.wei.myspring.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Describe PService
 * @Author a_pen
 * @Date 2020年09月08日
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@PComponent
public @interface PService {
    @AliasFor(annotation = PComponent.class)
    String value() default "";
}