package com.yang.http.demo.checkurl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HeadDemo {
    public static void main(String[] args) {
        String imageUrl = "https://img0.baidu.com/it/u=3032776730,2178451350&fm=253&fmt=auto&app=120&f=JPEG?w=1280&h=800";

        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(3000); // 设置连接超时时间为3秒
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("The image URL exists.");
            } else {
                System.out.println("The image URL does not exist.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while checking the image URL: " + e.getMessage());
        }
    }
}
