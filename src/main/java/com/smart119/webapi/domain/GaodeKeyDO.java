package com.smart119.webapi.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;

import java.io.Serializable;


/**
 * 高德服务key实体
 *
 * @author wangguangyu
 * @email wangguangyu@qq.com
 * @date 2021-04-30 10:30:11
 */
@Data
@TableName("gaode_key")
@ApiModel(value = "高德key的实体类", description = "高德key的实体类")
public class GaodeKeyDO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "主键ID", name = "id")
    private Integer id;

    /**
     * 高德key
     */
    @ApiModelProperty(value = "高德key", name = "key")
    @TableField(value = "`key`")
    private String key;

    /**
     * 类型（ 0：webapi(web端(js)) 1:restapi，2：Android平台；3、iOS平台；4、微信小程序； 5、智能硬件）
     */
    @ApiModelProperty(value = "类型（ 0：webapi(web端(js)) 1:Web服务；2、Android平台；3、iOS平台； 4、微信小程序；5、智能硬件）", name = "type")
    @TableField(value = "`type`")
    private Integer type;

    /**
     * 状态（0 可用 1：不可用）
     */
    @ApiModelProperty(value = "状态（0 可用 1：不可用）", name = "status")
    @TableField(value = "`status`")
    private Integer status;

}
