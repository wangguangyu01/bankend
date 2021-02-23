package com.smart119.common.domain;

/**
 * @ClassName : Message
 * @Description : Message
 * @Author : Liangsl
 * @Date: 2021-01-23 14:08
 */
public class Message {
    //业务类型
    private String bussinessType;
    //业务Id
    private String bussinessId;
    // webSocketUrl
    private String webSocketUrl;
    // 接收人id
    private String receiveUserId;
    // 接收组织机构id
    private String receiveOrgId;
    // 接收人姓名
    private String userName;
    // 接收人组织机构名
    private String orgName;
    // 唯一值
    private String uuid;
    //消息内容
    private String msg;

    public String getBussinessType() {
        return bussinessType;
    }

    public void setBussinessType(String bussinessType) {
        this.bussinessType = bussinessType;
    }

    public String getBussinessId() {
        return bussinessId;
    }

    public void setBussinessId(String bussinessId) {
        this.bussinessId = bussinessId;
    }

    public String getWebSocketUrl() {
        return webSocketUrl;
    }

    public void setWebSocketUrl(String webSocketUrl) {
        this.webSocketUrl = webSocketUrl;
    }

    public String getReceiveUserId() {
        return receiveUserId;
    }

    public void setReceiveUserId(String receiveUserId) {
        this.receiveUserId = receiveUserId;
    }

    public String getReceiveOrgId() {
        return receiveOrgId;
    }

    public void setReceiveOrgId(String receiveOrgId) {
        this.receiveOrgId = receiveOrgId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
