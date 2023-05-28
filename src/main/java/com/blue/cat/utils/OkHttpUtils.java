package com.blue.cat.utils;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 请求微信接口获取相关Json信息
 */
@Slf4j
public class OkHttpUtils {

    /**
     * OkHttpClient使用了连接池，应保持一个实例存在
     * 可以定制配置连接池
     */
    private static final OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
            .connectTimeout(180,TimeUnit.SECONDS)
            .readTimeout(180,TimeUnit.SECONDS)
            .writeTimeout(180,TimeUnit.SECONDS)
            .build();

    public static String getReq(String url, Map<String, String> headers) throws Exception {
        Request.Builder request = new Request.Builder().url(url);
        if(!headers.isEmpty()){
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                request.addHeader(entry.getKey(), entry.getValue());
            }
        }

        Response response = okHttpClient.newCall(request.build()).execute();
        if (response.body() == null) {
            return null;
        }
        String result = response.body().string();
        response.close();
        return result;
    }

    public static String postReq(String url, String body, Map<String, String> headers) throws Exception {
        Request.Builder req = new Request.Builder().url(url);
        if(!headers.isEmpty()){
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                req.addHeader(entry.getKey(), entry.getValue());
            }
        }

        if(body != null){
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), body);
            req.post(requestBody);
        }

        Response response = okHttpClient.newCall(req.build()).execute();
        String result = response.body() == null ? null : response.body().string();
        response.close();
        return result;
    }
}

