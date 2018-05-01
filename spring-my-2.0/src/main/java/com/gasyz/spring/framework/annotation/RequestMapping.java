package com.gasyz.spring.framework.annotation;

import java.lang.annotation.*;

/**
 * Created by gaoang on 2018/4/28.
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestMapping {
    String value() default  "";
}
