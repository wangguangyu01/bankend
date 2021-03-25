package com.smart119.jczy.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 外部联系人
 *
 * @author shichengyuan
 * @email shichengyuan@sz000673.com
 * @date 2021-03-24 17:25:55
 */
@Data
@TableName("txl_wblxr")
@ApiModel(value = "外部联系人", description = "外部联系人对象")
public class WblxrExcelDO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名", name = "xm")
    private String xm;

    /**
     * 电话
     */
    @ApiModelProperty(value = "电话", name = "dh")
    private String dh;

    /**
     * 地址
     */
    @ApiModelProperty(value = "地址", name = "dz")
    private String dz;

    /**
     * 生日
     */
    @ApiModelProperty(value = "生日", name = "sr")
    private Date sr;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱", name = "yx")
    private String yx;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", name = "bz")
    private String bz;



}
