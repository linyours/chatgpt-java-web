package com.blue.cat.config;

import com.blue.cat.interceptor.ChatGptInterceptor3;
import com.blue.cat.interceptor.LoginInterceptor;
import com.blue.cat.interceptor.VisitLimitInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Autowired
    private ChatGptInterceptor3 chatGptInterceptor3;

    @Autowired
    private VisitLimitInterceptor visitLimitInterceptor;


    /**
     * 支持跨域
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedOrigins("*")
                .allowedMethods("*");
    }

    // 临时放开 回话限制
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/chat/createTask","/chat/drawTaskResult","/prompt/getPromptGroup","/popupInfo/getPopupInfo","/userInfo/login","/userInfo/register","/check/text")
                .order(-1);
        registry.addInterceptor(visitLimitInterceptor).addPathPatterns("/**")
                .order(1);
        registry.addInterceptor(chatGptInterceptor3).addPathPatterns("/chat/streamChatWithWeb/V3")
                .order(2);
    }
}

