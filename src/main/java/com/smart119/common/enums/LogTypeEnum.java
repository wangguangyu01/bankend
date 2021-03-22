package com.smart119.common.enums;

public enum LogTypeEnum {

	RUNNING("运行日志"),
	DEBUG("调试日志");
	
	private String code;
	
	LogTypeEnum(String code){
		this.code= code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
}
