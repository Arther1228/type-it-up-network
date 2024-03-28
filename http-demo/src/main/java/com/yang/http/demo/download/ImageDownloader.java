package com.yang.http.demo.download;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class ImageDownloader {

    public static void downloader(String imageUrl, String savePath){
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();

                try (FileOutputStream outputStream = new FileOutputStream(savePath)) {
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    System.out.println("图片下载完成：" + savePath);
                }
            } else {
                System.out.println("图片下载失败");
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String imageUrl = "https://img1.baidu.com/it/u=1304255642,2961408783&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500";
        String savePath = "C:\\Users\\HP\\Desktop\\TEMP\\image1.jpg";

        Instant startTime = Instant.now();
        for (int i = 0; i < 50; i++) {
            downloader(imageUrl, savePath);
        }

        Instant imageCutEndTime = Instant.now();
        long seconds = ChronoUnit.MILLIS.between(startTime, imageCutEndTime);
        System.out.println("图片下载消耗时间(毫秒)：" + seconds);
    }
}
