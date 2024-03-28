package com.yang.http.demo.checkurl;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author yangliangchuang 2023/7/6 9:36
 */
public class OptionsDemo {

    public static Integer testHttpUrl(String path) {
        Integer responseCode = 200;
        try {
            java.net.URL url = new java.net.URL(path);
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
            conn.setRequestMethod("OPTIONS");
            //conn.setRequestMethod(task.getRequestMethod().toString());
            // 设置连接超时时间为5秒
            conn.setConnectTimeout(5 * 1000);
            // 设置读取超时时间为20秒
            conn.setReadTimeout(20 * 1000);
            // 设置输入流采用字节流
            conn.setDoInput(true);
            // 设置输出流采用字节流
            conn.setDoOutput(true);
            // 设置缓存
            conn.setUseCaches(false);
            conn.setRequestProperty("Test-Token", "SunCreate");
            conn.setRequestProperty("Charset", "utf-8");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("Charset", "utf-8");
            conn.connect();

            BufferedReader bf = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String tempStr = null;
            String resultStr = "";
            while ((tempStr = bf.readLine()) != null) {
                resultStr += tempStr;
            }
            bf.close();

            String temp;
            // 如果请求响应码是200，则表示成功
            responseCode = conn.getResponseCode();
            // 断开连接
            conn.disconnect();
        } catch (Exception e) {
        }

        return responseCode;
    }

    public static void main(String[] args) {

        String url = "https://www.baidu.com/";
        Integer responseCode = OptionsDemo.testHttpUrl(url);
        System.out.println("url连通检测responseCode：" + responseCode);
    }
}
