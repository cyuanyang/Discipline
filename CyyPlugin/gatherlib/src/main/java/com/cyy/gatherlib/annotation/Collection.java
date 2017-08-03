package com.cyy.gatherlib.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by study on 17/7/19.
 * 这个注解用于自定义要收集的方法
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Collection {

    boolean isGather() default true; //该方法是否需要收集 默认true
}
