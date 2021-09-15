package com.smart119.jczy.domain;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author shichengyuan
 * @email shichengyuan@sz000673.com
 * @date 2021-09-15 08:15:15
 */
@Data
@TableName("jczy_wxhxp_hz")
@ApiModel(value = "", description = "对象")
public class WxhxpHzDO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId
    @ApiModelProperty(value = "", name = "id")
    private String id;

    /**
     * 中文名
     */
    @ApiModelProperty(value = "中文名", name = "cnName")
    private String cnName;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述", name = "content")
    private String content;

}
