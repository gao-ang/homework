package com.gasyz.spring.framework.annotation;

import java.lang.annotation.*;

/**
 * Created by gaoang on 2018/4/28.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Service {
    String value() default  "";
}
