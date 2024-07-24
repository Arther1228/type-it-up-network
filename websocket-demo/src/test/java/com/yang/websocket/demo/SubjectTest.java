package com.yang.websocket.demo;

import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import javax.net.ssl.*;
import java.net.Socket;
import java.net.URI;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yangliangchuang
 * @date 2024/7/24 19:42
 */
@Slf4j
public class SubjectTest {

    public void testWssWithWebSocketServer() throws Exception {
        TrustManager trustManager = new X509ExtendedTrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType, Socket socket) throws CertificateException {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType, Socket socket) throws CertificateException {

            }

            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType, SSLEngine engine) throws CertificateException {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType, SSLEngine engine) throws CertificateException {

            }

            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[]{};
            }
        };
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, new TrustManager[]{trustManager}, null);

        //此处填写你需要连接的WebSocket地址
        URI uri = URI.create("wss://127.0.0.1:8800/websocket");
        SSLSocketFactory socketFactory = sslContext.getSocketFactory();
        WebSocketClient webSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                log.info("建立连接");
            }

            @Override
            public void onMessage(String s) {
                log.info("收到来自服务端的消息:::" + s);
            }

            @Override
            public void onClose(int i, String s, boolean b) {
                log.info("关闭连接:::" + "i = " + i + ":::s = " + s + ":::b = " + b);
            }

            @Override
            public void onError(Exception e) {
                log.info("报错了:::" + e.getMessage());
            }
        };
        webSocketClient.setSocketFactory(socketFactory);
        webSocketClient.connectBlocking();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //发送消息
        webSocketClient.send(simpleDateFormat.format(new Date()));
        //断开连接
        webSocketClient.closeBlocking();
    }
}
