package com.yang.http.demo.request;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HttpClientHelper {
    private static void sendGetRequest(String url) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);

        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                System.out.println("GET请求结果：" + result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String sendPostRequest(String url) {
        String postData = "ip=1&name=2";
        String result = "";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);

        try {
            List<NameValuePair> params = new ArrayList<>();
            String[] keyValuePairs = postData.split("&");
            for (String keyValuePair : keyValuePairs) {
                String[] keyValue = keyValuePair.split("=");
                params.add(new BasicNameValuePair(keyValue[0], keyValue[1]));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(params));

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    result = EntityUtils.toString(entity);
                    System.out.println("POST请求结果：" + result);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        String url = "https://www.baidu.com?ip=120.79.75.96";
        sendGetRequest(url);
        sendPostRequest(url);
    }
}
