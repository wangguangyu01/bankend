package com.smart119.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by mac on 17/8/22.
 */
@Configuration
@ConfigurationProperties(prefix = "ftp")
@Data
public class FtpConfig {

    //FTP IP地址
    private String ip;
    //FTP用户名
    private String user;
    //FTP密码
    private String password;

    private String uploadFolder;
}
