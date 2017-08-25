package com.dengzi.lib.ioc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Title:点击事件的绑定注解类
 * @Author: djk
 * @Time: 2017/8/2
 * @Version:1.0.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BindClick {
    int[] value();
}
