package com.gasyz.spring.annotation;

import java.lang.annotation.*;

/**
 * Created by gaoang on 2018/4/28.
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestParam {
    String value() default  "";
}
