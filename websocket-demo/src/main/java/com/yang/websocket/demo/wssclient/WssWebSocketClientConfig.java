package com.yang.websocket.demo.wssclient;

import javax.net.ssl.*;
import java.net.Socket;
import java.net.URI;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class WssWebSocketClientConfig {

    public static SSLSocketFactory createSSLSocketFactory() throws Exception {
        TrustManager trustManager = new X509ExtendedTrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType, Socket socket) throws CertificateException {}

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType, Socket socket) throws CertificateException {}

            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType, SSLEngine engine) throws CertificateException {}

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType, SSLEngine engine) throws CertificateException {}

            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[]{};
            }
        };
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, new TrustManager[]{trustManager}, null);
        return sslContext.getSocketFactory();
    }

    public static URI getWebSocketURI() {
        return URI.create("wss://10.8.0.60/sub/subscriptions?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6ImQ0NjEzYWZlLTRlOTUtMTFlYy1iMmZiLWVmOTgxNmVkYzNmZCIsImNvbnRyb2xsZXJfaWQiOiJsb2NhbC1tYXN0ZXIiLCJpYXQiOjE3MjE5MDQ1ODUsImV4cCI6MTc1MzQ0MDU4NX0.vS4zivJ_KA4hLzzaXbZqzbI90yj7EIXPsjSsqpllV90");
    }
}
