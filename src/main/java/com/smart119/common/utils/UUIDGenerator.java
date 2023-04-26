package com.smart119.common.utils;

import ch.qos.logback.core.net.SyslogOutputStream;

import java.util.UUID;


/**
 * uuid主键策略
 * @author wangguangyu
 *
 */
public class UUIDGenerator {

    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        // 去掉"-"符号
        String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
        return temp;
    }

}
