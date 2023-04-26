package com.smart119.common.annotation.validator;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Range;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;

import java.lang.reflect.Field;

@Slf4j
public class BindingResultError {

    /**
     * 如果是Integer类型的话，超长会出现字符串转数字的异常，无法正确返回错误信息，
     * 如果以后需要扩展错误可以在这里继续修改，新增if判断
     * @param clzz 校验的对象的类
     * @param bindingResult 校验的时候错误的信息
     * @return
     * @throws Exception
     */
    public static String getBindingResultError(Class clzz, BindingResult bindingResult) throws Exception {
        if (!ObjectUtils.isEmpty(bindingResult) && !ObjectUtils.isEmpty(bindingResult.getFieldError())) {
            // 输入校验不合法的字段名字
            String field = bindingResult.getFieldError().getField();
            // 根据实体，获取到错误字段
            Field fieldObj = clzz.getDeclaredField(field);
            // 根据实体，获取到错误字段对应的类型
            String classTypeName = fieldObj.getType().getSimpleName();
            // 因为在校验的时候有一个字符串转换成数字的异常，所以不会正常抛出错误提示，所以需要判断
            if (StringUtils.equalsIgnoreCase(classTypeName, "Integer")) {
                Range range = fieldObj.getAnnotation(Range.class);
                return range.message();
            }
        }
        return "";
    }
}
