package com.ni235.common.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

public class HttpUtils {

    public static HttpEntity httpGet(String url) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpGet httpGet = new HttpGet(url);
//            HttpHost proxy = new HttpHost(ip, port);
            // 设置认证
//            CredentialsProvider provider = new BasicCredentialsProvider();
//            provider.setCredentials(new AuthScope(proxy), new UsernamePasswordCredentials("cyu", "cyu123456"));
//            CloseableHttpClient httpClient = HttpClients.custom().setDefaultCredentialsProvider(provider).build();
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(15000)
                    .setSocketTimeout(15000)
                    .setConnectionRequestTimeout(30000)
//                    .setProxy(proxy)
                    .build();
            httpGet.setConfig(requestConfig);
            httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.119 Safari/537.36");
            // 响应模型
            HttpResponse httpResponse = httpClient.execute(httpGet);
            return httpResponse.getEntity();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return null;
    }



}
