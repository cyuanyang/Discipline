package com.cyy.gatherlib.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by study on 17/7/21.
 * 如果不想这个activity的显示和隐藏被统计 则用这个注解
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented

public @interface GatherActivity {
    boolean isGather() default true;
}
