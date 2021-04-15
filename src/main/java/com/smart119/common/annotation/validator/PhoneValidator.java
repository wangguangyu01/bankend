package com.smart119.common.annotation.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 手机号码验证类
 */
@Documented
@Retention(RUNTIME)
@Target({FIELD, METHOD,PARAMETER})
@Constraint(validatedBy = PhoneValidatotClass.class)
public @interface PhoneValidator {
    String message()  default "手机号码格式不正确";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
