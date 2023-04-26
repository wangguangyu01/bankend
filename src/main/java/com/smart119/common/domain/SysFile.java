package com.smart119.common.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 文件上传
    */
@ApiModel(value="com-smart119-common-domain-SysFile")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_file")
public class SysFile {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="")
    private Long id;

    /**
     * 文件类型
     */
    @TableField(value = "type")
    @ApiModelProperty(value="文件类型, 1:内容附件;2:收款二维码；3：微信二维码")
    private Integer type;

    /**
     * 腾讯返回的URL地址
     */
    @TableField(value = "url")
    @ApiModelProperty(value="腾讯返回的URL地址")
    private String url;

    /**
     * 腾讯上传文件返回的文件id
     */
    @TableField(value = "file_id")
    @ApiModelProperty(value="腾讯上传文件返回的文件id")
    private String fileId;

    /**
     * 腾讯显示文件链接的开始请求的时间
     */
    @TableField(value = "request_time")
    @ApiModelProperty(value="腾讯显示文件链接的开始请求的时间")
    private Date requestTime;

    /**
     * 腾讯显示文件链接过期时间
     */
    @TableField(value = "expire_time")
    @ApiModelProperty(value="腾讯显示文件链接过期时间")
    private Date expireTime;

    /**
     * 创建时间
     */
    @TableField(value = "create_date")
    @ApiModelProperty(value="创建时间")
    private Date createDate;


    /**
     * 文章uuid
     */
    @TableField(value = "content_id")
    @ApiModelProperty(value="文章id")
    private String contentId;
}
