package com.yang.http.demo.download;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class FtpDemo {
    public static void main(String[] args) {
        // FTP服务器信息
        String ftpUrl = "ftp://your_ftp_host/path/to/remote/image.jpg";

        // 本地保存的文件路径
        String localPath = "/path/to/save/image.jpg";

        try {
            // 创建URL连接
            URL url = new URL(ftpUrl);
            URLConnection conn = url.openConnection();
            conn.setConnectTimeout(5000); // 设置连接超时时间
            conn.setReadTimeout(5000); // 设置读取超时时间
            conn.setDoInput(true);

            // 输入流
            InputStream in = conn.getInputStream();
            // 输出流
            FileOutputStream out = new FileOutputStream(localPath);

            // 下载文件
            byte[] buffer = new byte[1024];
            int len;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            System.out.println("文件下载完成！");

            // 关闭流
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
