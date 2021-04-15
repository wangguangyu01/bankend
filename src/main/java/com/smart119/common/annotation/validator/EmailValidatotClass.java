package com.smart119.common.annotation.validator;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 手机号码验证
 */
public class EmailValidatotClass implements ConstraintValidator<EmailValidator, String> {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if(StringUtils.isBlank(value)){
            return false;
        }
        Matcher m = EMAIL_PATTERN.matcher(value);
        return m.matches();
    }

    public static void main(String[] args) {
        String email = "45@qc.co";
        System.out.println(EMAIL_PATTERN.matcher(email).matches());
    }

}
