package com.smart119.common.controller;

import com.smart119.common.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 * @ClassName : PushMessageController
 * @Description : 推送websock接口
 * @Author : Liangsl
 * @Date: 2021-01-23 13:58
 */
@Controller
public class PushMessageController {

    @Autowired
    SimpMessagingTemplate template;

    @MessageMapping("/websocket/pushMessage")
    public void pushMessage(Message message){
        template.convertAndSend(message.getWebSocketUrl(), message.getMsg());
    }
}
