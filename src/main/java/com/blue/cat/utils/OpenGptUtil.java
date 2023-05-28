package com.blue.cat.utils;

/**
 * @author huyd
 * @date 2023/5/2 7:43 PM
 */

import com.alibaba.fastjson.JSONObject;
import com.blue.cat.bean.gpt.ChatReq;
import com.blue.cat.bean.gpt.ImageReq;
import com.blue.cat.bean.gpt.drawImageRes;
import com.google.common.collect.Lists;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Slf4j
public class OpenGptUtil {

    public static void main(String[] args) {
    }

    public static void stramChatV2() {
        try {
            ChatMessage message = new ChatMessage();
            message.setContent("香辣嗦螺怎么做好吃");
            message.setRole("system");

            ChatCompletionRequest request = new ChatCompletionRequest();
            request.setMessages(Lists.newArrayList(message));
            request.setUser("123");
            request.setModel("gpt-3.5-turbo");

            // 指定接口地址
            String req = "http://localhost:8088/api/stream?request=%s";
            URL url = new URL(String.format(req, URLEncoder.encode(JSONObject.toJSONString(request), "UTF-8")));

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "text/event-stream");

            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setBodyParameter(String str, HttpURLConnection conn) throws IOException {
        try (DataOutputStream out = new DataOutputStream(conn.getOutputStream())) {
            out.write(str.getBytes(StandardCharsets.UTF_8));
            out.flush();
        } catch (Exception e) {
            System.out.println("setBodyParameter error:" + e);
        }
    }
}
