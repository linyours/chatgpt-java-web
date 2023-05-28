package com.blue.cat.bean.constants;

public class CommonConstant {

    /**
     * 登录成功 cookie的key
     */
    public final static String TOKEN = "blueCat_token";

    /**
     * 登录失效三小时
     */
    public final static int CACHE_TIME_OUT = 10800;

    /**
     * 通用的敏感词过滤通道ID
     */
    public final static Long COMMON_FILTER_CHANNEL_ID = 22L;

    public final static Long COMMON_REGEX_CHANNEL_ID = 5232962780960694399L;

    /**
     * 公众号的二维码
     */
    public final static String OFFICIAL_ACCOUNT = "http://43.153.112.145:1002/wechat.jpeg";
    /**
     * 聊天的来源
     */
    public final static String CHAT_SOURCE = "chat";

    public final static String PROMPT_TOOL_SOURCE = "AITool";

    /**
     * 限流规则ip
     */
    public final static String LIMIT_IP = "IP";

    /**
     * 如果用户没有登录的话 最多访问次数
     */
    public final static Integer LIMIT_IP_COUNT = 10;

    /**
     * 默认十秒才能发一次话
     */
    public final static Integer CHAT_LIMIT_TIME = 5;

    /**
     * 登录的作用域
     */
    public final static int LOGIN_SCOPE = 1;


    /**
     * 不登录的作用域
     */
    public final static int NO_LOGIN_SCOPE = 2;


    /**
     * 所有的作用域
     */
    public final static int ALL_SCOPE = 3;
}
