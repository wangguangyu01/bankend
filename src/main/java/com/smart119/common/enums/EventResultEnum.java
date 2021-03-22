package com.smart119.common.enums;

public enum EventResultEnum {

	SUCCESS("操作成功"),
	ERROR("操作失败");
	
	private String code;
	
	EventResultEnum(String code){
		this.code= code;
	}
	
	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}




}
