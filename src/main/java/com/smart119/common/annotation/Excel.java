package com.smart119.common.annotation;

import java.lang.annotation.*;

/**
 *
 * 报表注解
 * @author wangguangyu
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Excel {


    /**
     * 导出时在excel中排序
     */
    public int sort() default Integer.MAX_VALUE;

    /**
     * 导出到Excel中的名字.
     */
    public String name() default "";



}
