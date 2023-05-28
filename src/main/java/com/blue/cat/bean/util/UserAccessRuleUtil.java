package com.blue.cat.bean.util;

import com.blue.cat.bean.constants.ModelServiceTypeConstant;
import com.blue.cat.bean.entity.UserAccessRule;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 用户chat-gpt的默认配置
 */
@Getter
public class UserAccessRuleUtil{

    /**
     * 模型3的默认配置
     * @param userId
     * @return
     */
    public static UserAccessRule getAccessRuleGpt3(Long userId){
        UserAccessRule userAccessRule = new UserAccessRule();
        userAccessRule.setUserId(userId);
        userAccessRule.setServiceType(ModelServiceTypeConstant.CHAT_GPT_MODEL3);
        userAccessRule.setUseNumber(30);
        userAccessRule.setStartEffectiveTime(LocalDateTime.now());
        userAccessRule.setEndEffectiveTime(LocalDateTime.now().plusDays(1));
        userAccessRule.setCreateTime(LocalDateTime.now());
        return userAccessRule;
    }

    /**
     * 模型4的默认配置
     * @param userId
     * @return
     */
    public static UserAccessRule getAccessRuleGpt4(Long userId){
        UserAccessRule userAccessRule = new UserAccessRule();
        userAccessRule.setUserId(userId);
        userAccessRule.setServiceType(ModelServiceTypeConstant.CHAT_GPT_MODEL4);
        userAccessRule.setUseNumber(30);
        userAccessRule.setStartEffectiveTime(LocalDateTime.now());
        userAccessRule.setEndEffectiveTime(LocalDateTime.now().plusDays(1));
        userAccessRule.setCreateTime(LocalDateTime.now());
        return userAccessRule;
    }

    public static void main(String[] args) {
        System.out.println(UserAccessRuleUtil.getAccessRuleGpt3(123L));
    }
}
