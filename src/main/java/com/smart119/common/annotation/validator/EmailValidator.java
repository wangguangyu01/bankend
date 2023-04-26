package com.smart119.common.annotation.validator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 邮箱验证类
 */
@Documented
@Retention(RUNTIME)
@Target({FIELD, METHOD,PARAMETER})
@Constraint(validatedBy = EmailValidatotClass.class)
public @interface EmailValidator {
    String message()  default "邮箱格式不正确";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
