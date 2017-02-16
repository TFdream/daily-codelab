package com.bytebeats.code.orm.annotation;

import java.lang.annotation.*;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @date 2017-01-03 17:44
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.CLASS)
@Documented
public @interface Mapper {
    String pkg();
}
