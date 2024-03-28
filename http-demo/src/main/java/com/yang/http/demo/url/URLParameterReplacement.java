package com.yang.http.demo.url;

import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class URLParameterReplacement {
    public static String replaceParameterValue(String urlString, String paramName, String newValue) {
        try {
            URL url = new URL(urlString);
            String query = url.getQuery();

            if (query != null) {
                Map<String, String> parameters = new HashMap<>();
                String[] pairs = query.split("&");

                // 解析参数并存储在Map中
                for (String pair : pairs) {
                    String[] keyValue = pair.split("=");
                    if (keyValue.length == 2) {
                        String key = URLDecoder.decode(keyValue[0], "UTF-8");
                        String value = URLDecoder.decode(keyValue[1], "UTF-8");
                        parameters.put(key, value);
                    }
                }

                // 替换指定参数的值
                parameters.put(paramName, newValue);

                // 构建新的URL
                StringBuilder newQuery = new StringBuilder();
                for (Map.Entry<String, String> entry : parameters.entrySet()) {
                    if (newQuery.length() > 0) {
                        newQuery.append("&");
                    }
                    newQuery.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                    newQuery.append("=");
                    newQuery.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                }

                // 构建新的URL字符串
                String newUrlString = url.getProtocol() + "://" + url.getHost() + url.getPath() + "?" + newQuery.toString();

                return newUrlString;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return urlString; // 如果无法解析URL或参数不存在，则返回原始URL
    }

    public static void main(String[] args) {
        String urlString = "https://example.com/path?param1=value1&param2=value2";
        String paramName = "param1";
        String newValue = "newvalue1";

        String newUrlString = replaceParameterValue(urlString, paramName, newValue);

        System.out.println("New URL: " + newUrlString);
    }
}
