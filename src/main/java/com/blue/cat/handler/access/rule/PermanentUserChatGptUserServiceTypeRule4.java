package com.blue.cat.handler.access.rule;

import com.blue.cat.bean.constants.ModelServiceTypeConstant;
import com.blue.cat.bean.constants.UserLevelConstant;
import com.blue.cat.bean.vo.UserLevelAccessVo;
import org.springframework.stereotype.Component;


/**
 * 尊贵的付费用户
 * 调用chat-gpt3模型的 随便玩
 */
@Component(UserLevelConstant.PERMANENT_USERS + ModelServiceTypeConstant.CHAT_GPT_MODEL4)
public class PermanentUserChatGptUserServiceTypeRule4 implements UserServiceTypeRule<UserLevelAccessVo> {

    @Override
    public boolean rule(String userLevel, UserLevelAccessVo accessVo) {
        return true;
    }
}
