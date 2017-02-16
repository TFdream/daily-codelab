package com.bytebeats.code.annotation.processor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 编译时注解
 *
 * @author Ricky Fung
 * @date 2016-12-28 11:26
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.CLASS)
public @interface Factory {
    String id() default "";
    Class<?> type();
}
