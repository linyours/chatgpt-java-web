package com.blue.cat.handler.access.rule;

import com.blue.cat.bean.constants.ModelServiceTypeConstant;
import com.blue.cat.bean.constants.UserLevelConstant;
import com.blue.cat.bean.vo.UserLevelAccessVo;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


/**
 * 时长用户
 * 调用chat-gpt3模型的 根据时长范围 原则上不限制次数
 */
@Component(UserLevelConstant.DURATION_USER + ModelServiceTypeConstant.CHAT_GPT_MODEL4)
public class DurationUserChatGptUserServiceTypeRule4 implements UserServiceTypeRule<UserLevelAccessVo> {

    @Override
    public boolean rule(String userLevel, UserLevelAccessVo accessVo) {
        LocalDateTime nowDate = LocalDateTime.now();
        LocalDateTime expirationDate = accessVo.getEndEffectiveTime();
        int diff = expirationDate.compareTo(nowDate);
        return diff>=0; //过期时间是否大于当前时间
    }

    public static void main(String[] args) {
        System.out.println(LocalDateTime.now());
    }
}
