package com.yang.websocket.demo.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;

import java.io.IOException;

@Component
public class WebSocketClientService {

    private final WebSocketClient webSocketClient;
    private final WebSocketHandler webSocketHandler;
    private WebSocketSession session;

    @Autowired
    public WebSocketClientService(WebSocketClient webSocketClient, WebSocketHandler webSocketHandler) {
        this.webSocketClient = webSocketClient;
        this.webSocketHandler = webSocketHandler;
    }

    public void connect() {
        try {
            session = webSocketClient.doHandshake(webSocketHandler, "ws://124.222.224.186:8800").get();
            // 连接建立后的逻辑
            System.out.println("连接 websocket成功.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message)  {
        // 发送消息的逻辑
        try {
            session.sendMessage(new TextMessage(message));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        // 断开连接的逻辑
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
