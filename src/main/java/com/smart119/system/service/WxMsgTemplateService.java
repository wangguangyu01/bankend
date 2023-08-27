package com.smart119.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smart119.system.domain.WxMsgTemplate;
import com.smart119.system.domain.WxTemplateMsgLog;


public interface WxMsgTemplateService extends IService<WxMsgTemplate> {



    /**
     * 发送订阅消息
     * @param openId
     * @param wxMsgTemplate
     * @return
     */
    public WxTemplateMsgLog sendWxMsg(String openId, WxMsgTemplate wxMsgTemplate) throws Exception;


    /**
     * 根据模板类型查询
     * @param templateType
     * @return
     */
    WxMsgTemplate queryOne(Integer templateType);

}

