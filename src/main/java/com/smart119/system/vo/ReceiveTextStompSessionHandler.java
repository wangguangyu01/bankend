package com.smart119.system.vo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

/**
 * @author: zhangshunhua
 * @date: 2021/3/8 12:45
 */
@Slf4j
public class ReceiveTextStompSessionHandler extends StompSessionHandlerAdapter {

  @Override
  public void handleFrame(StompHeaders headers, Object payload) {
    log.info("接收订阅消息=" + (String) payload);
  }

  @Override
  public void handleTransportError(StompSession stompSession, Throwable exception) {
    log.error("",exception);
    //super.handleTransportError(stompSession, exception);
    try {
      Thread.sleep(3000);
      ReceiveTextStompClient.connect();
    } catch (InterruptedException e) {
      log.error("",e);
    }
  }
}
