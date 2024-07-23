package com.yang.http.demo.https;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @author chuang  待测试  比较 TLS （忽略证书）  ChatGPT
 */
public class HttpsRequestUtil {

    // 创建忽略证书验证的 SSLContext
    private static SSLContext createIgnoreVerifySSL() throws Exception {
        SSLContext sc = SSLContext.getInstance("TLS");

        // 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };

        sc.init(null, new TrustManager[]{trustManager}, new java.security.SecureRandom());
        return sc;
    }

    public static String sendPostRequest(String url, String params) throws Exception {
        // 创建忽略证书验证的 SSLSocketFactory
        SSLSocketFactory sslSocketFactory = createIgnoreVerifySSL().getSocketFactory();

        // 发送HTTPS POST请求
        HttpResponse response = HttpRequest.post(url)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json;charset=UTF-8")
                .setSSLSocketFactory(sslSocketFactory)
                .body(params)
                .execute();

        return response.body();
    }

    public static String sendGetRequest(String url, String auth) throws Exception {
        // 创建忽略证书验证的 SSLSocketFactory
        SSLSocketFactory sslSocketFactory = createIgnoreVerifySSL().getSocketFactory();

        // 发送HTTPS GET请求
        HttpResponse response = HttpRequest.get(url)
                .header("Authorization", auth)
                .header("Content-Type", "application/json;charset=UTF-8")
                .setSSLSocketFactory(sslSocketFactory)
                .execute();

        return response.body();
    }

}
