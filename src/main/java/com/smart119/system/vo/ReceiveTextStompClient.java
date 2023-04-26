package com.smart119.system.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

/**
 * @author: zhangshunhua
 * @date: 2021/3/8 12:42
 */
@Slf4j
public class ReceiveTextStompClient {

  private static String url = "http://192.168.1.14:8088/message/endpoint";
  private static String token = "freeswitch_token";
  private static StompSession stompSession;// 定义全局变量，代表一个session

  public static void connect() {// 定义连接函数
    if (stompSession == null || !stompSession.isConnected()) {
      log.info("当前处于断开状态,尝试连接");
      List<Transport> transports = new ArrayList<>();
      transports.add(new WebSocketTransport(new StandardWebSocketClient()));
      SockJsClient sockJsClient = new SockJsClient(transports);
      WebSocketStompClient webSocketStompClient = new WebSocketStompClient(sockJsClient);
      webSocketStompClient.setMessageConverter(new StringMessageConverter());
      webSocketStompClient.setDefaultHeartbeat(new long[] { 20000, 0 });
      ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
      taskScheduler.afterPropertiesSet();
      webSocketStompClient.setTaskScheduler(taskScheduler);
      WebSocketHttpHeaders webSocketHttpHeaders = null;
      StompHeaders stompHeaders = new StompHeaders();
      stompHeaders.add("token", token);
      StompSessionHandler receiveTextStompSessionHandler = new ReceiveTextStompSessionHandler();
      try {
        ListenableFuture<StompSession> future = webSocketStompClient.connect(url, webSocketHttpHeaders,
            stompHeaders, receiveTextStompSessionHandler);
        stompSession = future.get();
        stompSession.setAutoReceipt(true);
        //stompSession.subscribe("/topic/wechat/message/receiveText/12345", receiveTextStompSessionHandler);
        // stompSession.send("/app/wechat/message/sendText", jsonString.getBytes());
      } catch (Exception e) {

      }
    } else {
      log.info("当前处于连接状态");
    }

  }

  public static void main(String[] args) {// 建立连接
    while (stompSession == null || !stompSession.isConnected()) {
      connect();// 连接服务器
      try {
        Thread.sleep(3000);// 连接服务器失败的处理 3秒后重新连接
      } catch (Exception e1) {
      }
    }
    new Scanner(System.in).nextLine();
  }
}
