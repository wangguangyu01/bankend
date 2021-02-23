package com.smart119.common.domain;

/**
 * @ClassName : Distance
 * @Description : 耗时
 * @Author : Liangsl
 * @Date: 2021-01-24 17:00
 */
public class Duration {
    private String text;
    private Double value;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
