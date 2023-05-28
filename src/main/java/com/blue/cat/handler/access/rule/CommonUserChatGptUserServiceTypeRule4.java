package com.blue.cat.handler.access.rule;

import com.blue.cat.bean.constants.ModelServiceTypeConstant;
import com.blue.cat.bean.constants.UserLevelConstant;
import com.blue.cat.bean.vo.UserLevelAccessVo;
import org.springframework.stereotype.Component;


/**
 * 普通用户
 * 调用chat-gpt3模型的 每天 10次
 */
@Component(UserLevelConstant.COMMON_USER + ModelServiceTypeConstant.CHAT_GPT_MODEL4)
public class CommonUserChatGptUserServiceTypeRule4 implements UserServiceTypeRule<UserLevelAccessVo> {

    @Override
    public boolean rule(String userLevel, UserLevelAccessVo accessVo) {

        int useNumber = accessVo.getUseNumber().get();
        if(useNumber>0){
            return true;
        }
        return false;
    }
}
