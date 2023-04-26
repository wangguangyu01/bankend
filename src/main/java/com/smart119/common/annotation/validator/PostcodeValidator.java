package com.smart119.common.annotation.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 邮编 校验
 */
@Documented
@Retention(RUNTIME)
@Target({FIELD, METHOD,PARAMETER})
@Constraint(validatedBy = PostcodeValidatotClass.class)
public @interface PostcodeValidator {
    String message()  default "电话号码格式不正确";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
