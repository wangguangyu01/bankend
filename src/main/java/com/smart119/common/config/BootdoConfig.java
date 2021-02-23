package com.smart119.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="smart119")
public class BootdoConfig {
	//上传路径
	private String uploadPath;

	private String username;

	private String password;

	private String baiduMapApiKey;

	private String gaodeMapApiKey;

	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBaiduMapApiKey() {
		return baiduMapApiKey;
	}

	public String getGaodeMapApiKey() {
		return gaodeMapApiKey;
	}

	public void setGaodeMapApiKey(String gaodeMapApiKey) {
		this.gaodeMapApiKey = gaodeMapApiKey;
	}

	public void setBaiduMapApiKey(String baiduMapApiKey) {
		this.baiduMapApiKey = baiduMapApiKey;
	}
}
