package com.bytebeats.codelab.bean.validation.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.lang.annotation.*;

@Max(10000)
@Min(8000)
@Constraint(validatedBy = {})
@Documented
@Target( { ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Price {
    String message() default "错误的价格";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}