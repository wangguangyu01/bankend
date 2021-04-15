package com.smart119.common.annotation.validator;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 邮编效验
 */
public class PostcodeValidatotClass implements ConstraintValidator<PostcodeValidator, String> {

    private static final Pattern POSTCODE_PATTERN = Pattern.compile("\\d{6}");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if(StringUtils.isBlank(value)){
            return false;
        }
        Matcher m = POSTCODE_PATTERN.matcher(value);
        return m.matches();
    }

    public static void main(String[] args) {
        String email = "2548531";
        System.out.println(POSTCODE_PATTERN.matcher(email).matches());
    }

}
