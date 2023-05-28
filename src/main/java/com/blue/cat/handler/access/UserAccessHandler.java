package com.blue.cat.handler.access;

import com.blue.cat.bean.entity.UserAccessRule;
import com.blue.cat.bean.req.UserAccessRuleUpdateReq;
import com.blue.cat.bean.vo.UserLevelAccessVo;
import com.blue.cat.service.impl.UserAccessRuleServiceImpl;
import com.blue.cat.utils.SessionUser;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class UserAccessHandler {

    @Resource
    private UserAccessRuleServiceImpl ruleService;

    private UserAccessRule updateReqToEntity(UserAccessRuleUpdateReq updateReq){
        UserAccessRule entity = new UserAccessRule();
        entity.setId(updateReq.getId());
        entity.setUseNumber(updateReq.getUseNumber());
        entity.setStartEffectiveTime(updateReq.getStartEffectiveTime());
        entity.setEndEffectiveTime(updateReq.getEndEffectiveTime());
        return entity;
    }

    /**
     * 更新
     * @param updateReq
     * @return
     */
    public boolean updateUserAccess(UserAccessRuleUpdateReq updateReq){
        UserAccessRule entity = updateReqToEntity(updateReq);
        entity.setUpdateUser(SessionUser.getAccount());
        entity.setUpdateTime(LocalDateTime.now());
        return ruleService.updateBatchById(Arrays.asList(entity));
    }


    /**
     * 获取用户的模型访问配置
     * @param userId
     * @return
     */
    public Map<String, UserLevelAccessVo> getUserAccessRule(Long userId){
        List<UserLevelAccessVo> userLevelAccessVos = ruleService.getAllAccessRuleByUserId(userId);
        return userLevelAccessVos.stream()
                .collect(Collectors.toMap(UserLevelAccessVo::getServiceType, Function.identity()));
    }

}
