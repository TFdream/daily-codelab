package com.bytebeats.code.orm.annotation;

import java.lang.annotation.*;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @date 2017-01-03 17:46
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.CLASS)
@Documented
public @interface Column {

    String value() default "";
}
