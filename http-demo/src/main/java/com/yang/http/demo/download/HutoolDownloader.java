package com.yang.http.demo.download;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpRequest;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * @author yangliangchuang 2023-11-14 10:51
 */
public class HutoolDownloader {

    public static void downloader(String imageUrl, String savePath){

        HttpRequest.get(imageUrl).execute().writeBody(FileUtil.file(savePath));

        System.out.println("图片下载完成：" + savePath);
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
