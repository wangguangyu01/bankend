package com.smart119.common.annotation.validator;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正整数效验
 */
public class PositiveIntegerValidatotClass implements ConstraintValidator<PositiveIntegerValidator, String> {

    private static final Pattern NUMBER_PATTERN = Pattern.compile("[1-9]\\d*");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if(StringUtils.isBlank(value)){
            return false;
        }
        Matcher m = NUMBER_PATTERN.matcher(value);
        return m.matches();
    }

    public static void main(String[] args) {
        String email = "2548";
        System.out.println(NUMBER_PATTERN.matcher(email).matches());
    }

}
