package com.smart119.common.dto;

import lombok.Data;

import java.io.Serializable;


@Data
public class GpsDto implements Serializable {


    /**
     * GPS登录用户uid（用户ID）
     */
    private String uid;

    /**
     * GPS登录用户的ukey（用户授权码）
     */
    private String uKey;

}
