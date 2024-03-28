package com.yang.http.demo.url;

import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class URLParser {
    public static Map<String, String> parseURLParameters(String urlString) {
        Map<String, String> parameters = new HashMap<>();

        try {
            URL url = new URL(urlString);
            String query = url.getQuery();

            if (query != null) {
                String[] pairs = query.split("&");
                for (String pair : pairs) {
                    String[] keyValue = pair.split("=");
                    if (keyValue.length == 2) {
                        String key = URLDecoder.decode(keyValue[0], "UTF-8");
                        String value = URLDecoder.decode(keyValue[1], "UTF-8");
                        parameters.put(key, value);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return parameters;
    }

    public static void main(String[] args) {
        String urlString = "https://example.com/path?param1=value1&param2=value2";

        Map<String, String> parameters = parseURLParameters(urlString);

        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            System.out.println("Parameter: " + entry.getKey() + ", Value: " + entry.getValue());
        }
    }
}
