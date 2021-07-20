package com.smart119.common.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.smart119.common.dto.GpsDto;

import java.util.List;

/**
 * EXLIVE平台动态数据
 *
 * @author wangguangyu
 * @email 1992lcg@163.com
 * @date 2021-07-07 15:45:15
 */
public interface ExliveService {


    /**
     * 用户登录
     *
     * @return 返回json数据：版本号，是否成功，车辆ID，车辆授权码
     */
    GpsDto clLoginSystem() throws JsonProcessingException;


    /**
     * 车辆分组
     *
     * @param uid  用户id
     * @param uKey 用户授权
     * @return
     * @throws JsonProcessingException
     */
    List<String> loadVehicles(String uid, String uKey) throws JsonProcessingException;


    /**
     * 查询车辆坐标
     *
     * @return
     */
    List<String> loadLocation() throws JsonProcessingException;


    public void updateXfclPostion() throws JsonProcessingException;


}
