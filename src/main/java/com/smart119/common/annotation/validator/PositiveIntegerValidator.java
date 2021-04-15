package com.smart119.common.annotation.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 正整数 - 验证类
 */
@Documented
@Retention(RUNTIME)
@Target({FIELD, METHOD,PARAMETER})
@Constraint(validatedBy = PositiveIntegerValidatotClass.class)
public @interface PositiveIntegerValidator {
    String message()  default "不是一个正整数格式";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
