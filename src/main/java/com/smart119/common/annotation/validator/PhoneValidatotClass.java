package com.smart119.common.annotation.validator;

import org.apache.commons.lang3.StringUtils;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 电话号码验证类 - 手机号、座机号
 */
public class PhoneValidatotClass implements ConstraintValidator<PhoneValidator, String> {

    /**
     * 固定电话 正则
     */
    private static final Pattern PHONE_PATTERN = Pattern.compile("[0-9-()（）]{7,18}");

    /**
     * 移动电话 正则
     */
    private static final Pattern MOBILE_PHONE_PATTERN = Pattern.compile("^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if(StringUtils.isBlank(value)){
            return false;
        }
        Matcher m = PHONE_PATTERN.matcher(value);

        if(m.matches()){
            return m.matches();
        }


        m = MOBILE_PHONE_PATTERN.matcher(value);
        return m.matches();

    }

    public static void main(String[] args) {
        String phone = "17912345678";
        System.out.println(PHONE_PATTERN.matcher(phone).matches());
    }

}
