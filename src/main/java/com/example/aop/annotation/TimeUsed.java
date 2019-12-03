package com.example.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author liuweibo
 * @date 2018/12/20
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TimeUsed {

    /**
     * 方法耗时 proj511第n次
     *
     * @return {@link Long}
     */
    long value() default 10L;

}
