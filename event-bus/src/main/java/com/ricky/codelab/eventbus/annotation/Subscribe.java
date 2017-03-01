package com.ricky.codelab.eventbus.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.ricky.codelab.eventbus.ThreadMode;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
@Documented
public @interface Subscribe {
	ThreadMode threadMode() default ThreadMode.POSTING;
	int priority() default 0;
}
