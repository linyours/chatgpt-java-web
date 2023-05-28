package com.blue.cat.handler.access.rule;

import com.blue.cat.bean.constants.CommonConstant;
import com.blue.cat.bean.constants.ModelServiceTypeConstant;
import com.blue.cat.bean.constants.UserLevelConstant;
import com.blue.cat.bean.vo.UserLevelAccessVo;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


/**
 * 普通用户
 * 调用chat-gpt3模型的 每天 10次
 */
@Component(UserLevelConstant.COMMON_USER + ModelServiceTypeConstant.CHAT_GPT_MODEL3)
public class CommonUserChatGptUserServiceTypeRule3 implements UserServiceTypeRule<UserLevelAccessVo> {

    @Override
    public boolean rule(String userLevel, UserLevelAccessVo accessVo) {
        LocalDateTime lastVisitDate = accessVo.getLastVisitDate();
        if(lastVisitDate==null){
            return true;
        }
        LocalDateTime canVisitTime = lastVisitDate.plusSeconds(CommonConstant.CHAT_LIMIT_TIME);
        LocalDateTime now = LocalDateTime.now();
        int diff = now.compareTo(canVisitTime);
        return diff>0;
//        int useNumber = accessVo.getUseNumber().get();
//        if(useNumber>0){
//            return true;
//        }
//        return false;
    }
}
