package com.smart119.blog.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.smart119.common.utils.poi.ExcelResources;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxActivityVo implements Serializable {

    @ExcelResources(title = "微信昵称", order = 1)
    private String nickname;


    /**
     * 微信号
     */
    @ExcelResources(title = "微信号", order = 2)
    private String wxNumber;





    /**
     * 联系电话
     */
    @ExcelResources(title = "联系电话", order = 3)
    private String phone;

    /**
     * 紧急联系人
     */
    @ExcelResources(title = "紧急联系人", order = 4)
    private String emergencyPhone;



    /**
     * 性别(0-未知、1-男、2-女)
     */
    @ExcelResources(title = "性别", order = 5)
    private String sex;

    /**
     * 身份证号
     */
    @ExcelResources(title = "身份证号", order = 6)
    private String card;


    @ExcelResources(title = "报名人住址", order = 7)
    private String address;



    /**
     * 创建时间
     */
    @ExcelResources(title = "报名时间", order = 8)
    private Date createTime;

}
