package com.yang.http.demo.download;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class HttpClientImageDownloader {

    public static void downloader(String imageUrl, String savePath){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(imageUrl);

        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            HttpEntity imageEntity = response.getEntity();
            if (imageEntity != null) {
                try (FileOutputStream outputStream = new FileOutputStream(savePath)) {
                    imageEntity.writeTo(outputStream);
                    System.out.println("图片下载完成：" + savePath);
                }
            } else {
                System.out.println("图片下载失败");
            }

            EntityUtils.consumeQuietly(imageEntity);
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
