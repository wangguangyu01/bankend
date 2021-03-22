package com.smart119.common.enums;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

/**
 * @author zhangshunhua
 * @date 2021/02/27
 */
@ApiModel(value="错误码",description="错误码")
@Getter
public enum ResponseStatusEnum {

	/** * 操作成功 */
	@ApiModelProperty(value="0",name="RESCODE_0",notes="操作成功")
	RESCODE_0(0, "操作成功"),

	@ApiModelProperty(value="404",name="RESCODE_404",notes="不存在该资源")
	RESCODE_404(404,"不存在该资源"),

	@ApiModelProperty(value="500",name="RESCODE_500",notes="服务器错误")
	RESCODE_500(500,"服务器异常"),

	@ApiModelProperty(value="10000",name="RESCODE_10000",notes="Token失效或未登陆")
	RESCODE_10000(10000,"Token失效或未登陆"),

	@ApiModelProperty(value="10001",name="RESCODE_10001",notes="服务暂停")
	RESCODE_10001(10001,"服务暂停"),

	@ApiModelProperty(value="10002",name="RESCODE_10002",notes="缺少source (api_key) 参数")
	RESCODE_10002(10002,"缺少source (api_key) 参数"),

	@ApiModelProperty(value="10003",name="RESCODE_10003",notes="非法请求")
	RESCODE_10003(10003,"非法请求"),

	@ApiModelProperty(value="10004",name="RESCODE_10004",notes="参数错误，请参考API文档")
	RESCODE_10004(10004,"参数错误，请参考API文档"),

	@ApiModelProperty(value="10005",name="RESCODE_10005",notes="用户请求频次超过上限")
	RESCODE_10005(10005,"用户请求频次超过上限"),

	@ApiModelProperty(value="10006",name="RESCODE_10006",notes="IP请求频次超过上限")
	RESCODE_10006(10006,"IP请求频次超过上限"),

	@ApiModelProperty(value="10007",name="RESCODE_10007",notes="缺少必要请求参数")
	RESCODE_10007(10007,"缺少必要请求参数"),

	@ApiModelProperty(value="10008",name="RESCODE_10008",notes="无权限访问")
	RESCODE_10008(10008,"无权限访问"),

	@ApiModelProperty(value="10009",name="RESCODE_10009",notes="接口签名错误")
	RESCODE_10009(10009,"接口签名错误"),


	@ApiModelProperty(value="2001001",name="RESCODE_2001001",notes="出动单确认失败")
	RESCODE_2001001(2001001,"出动单确认失败"),

	@ApiModelProperty(value="2001002",name="RESCODE_2001002",notes="未配置应用")
	RESCODE_2001002(2001002,"未配置应用")
	;

	/**
	 * 枚举的值
	 * */
	private int code;

	/**
	 * 枚举的中文描述
	 * */
	private String desc;


	ResponseStatusEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}


	public static String getDesc(int code) {
		for (ResponseStatusEnum b : ResponseStatusEnum.values()) {
			if (b.code == code) {
				return b.desc;
			}
		}
		return "";
	}


	/**
	 * 匹配提示码 未匹配的返回错误码1
	 * @param code
	 * @return
	 */
	public static ResponseStatusEnum getResponseStatusEnum(int code) {
		for (ResponseStatusEnum b : ResponseStatusEnum.values()) {
			if (b.code == code) {
				return b;
			}
		}
		return ResponseStatusEnum.RESCODE_500;
	}
}