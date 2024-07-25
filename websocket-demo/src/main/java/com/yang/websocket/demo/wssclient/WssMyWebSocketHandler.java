package com.yang.websocket.demo.wssclient;

import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

@Slf4j
public class WssMyWebSocketHandler extends WebSocketClient {

    public WssMyWebSocketHandler(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        log.info("建立连接");
    }

    @Override
    public void onMessage(String message) {
        log.info("收到来自服务端的消息: " + message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        log.info("关闭连接: code = " + code + ", reason = " + reason + ", remote = " + remote);
    }

    @Override
    public void onError(Exception ex) {
        log.error("发生错误: " + ex.getMessage(), ex);
    }
}
