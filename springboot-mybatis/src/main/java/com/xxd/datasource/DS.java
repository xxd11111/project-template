package com.xxd.datasource;

import java.lang.annotation.*;

/**
 * @Description: 多数据源注解
 * @Author: Lxq
 * @Date: 2019/10/18 11:10
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DS {
    String name() default "";
}
