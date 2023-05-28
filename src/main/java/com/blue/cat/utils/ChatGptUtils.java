package com.blue.cat.utils;

import com.alibaba.fastjson.JSONObject;
import com.blue.cat.bean.gpt.ChoiceMessage;
import com.blue.cat.bean.gpt.GptTurbo;
import com.blue.cat.bean.gpt.OpenReq;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author huyd
 * @date 2023/5/5 11:42 PM
 */
public class ChatGptUtils {

    public static void main(String[] args) throws Exception{
        String chat = chatWithSangiu(null, "西红柿炒鸡蛋怎么做");
        System.out.println(chat);
    }

    public static String chatWithSangiu(String systemDesc, String message) throws Exception {
        //String req = "http://amd2.watilion.top:8088/api/open/req";
        String req = "http://localhost:8088/api/open/req";
        String method = "POST";
        Map<String, String> map = new HashMap<>();
        map.put("Content-Type", "application/json");
        map.put("Authorization", "Bearer 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");

        String url = "https://api.openai.com/v1/chat/completions";
        List<ChoiceMessage> list = new ArrayList<>();
        if (StringUtils.isNotBlank(systemDesc)) {
            list.add(new ChoiceMessage("system", systemDesc));
        }
        list.add(new ChoiceMessage("user", "user question: " + message));

        GptTurbo gptTurbo = new GptTurbo();
        gptTurbo.setModel("gpt-3.5-turbo");
        gptTurbo.setMessages(list);
        String body = JSONObject.toJSONString(gptTurbo);

        OpenReq reqGpt = OpenReq.builder().method(method).url(url).body(body).headers(map).build();

        Map<String, String> headers = new HashMap<>(2);
        headers.put("Content-Type", "application/json");
        headers.put("gpt-token", "OldSanGiuNBPlus666");

        String result = OkHttpUtils.postReq(req, JSONObject.toJSONString(reqGpt), headers);
        if(StringUtils.isBlank(result)){
            return null;
        }

        JSONObject jsonObject = JSONObject.parseObject(result);
        int code = jsonObject.getIntValue("code");
        if(code != 0){
            return null;
        }
        String msg = jsonObject.getString("msg");
        JSONObject msgJb = JSONObject.parseObject(msg);
        Object choices = msgJb.getJSONArray("choices").get(0);
        Object messageContent = JSONObject.parseObject(choices.toString()).get("message");
        return JSONObject.parseObject(messageContent.toString()).getString("content");
    }

}
