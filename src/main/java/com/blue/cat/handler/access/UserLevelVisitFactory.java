package com.blue.cat.handler.access;

import com.blue.cat.bean.vo.CacheUserInfoVo;
import com.blue.cat.bean.vo.UserLevelAccessVo;
import com.blue.cat.utils.SessionUser;
import com.blue.cat.handler.access.rule.UserServiceTypeRule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Component
public class UserLevelVisitFactory implements ApplicationContextAware {

    private static Map<String, UserServiceTypeRule> userServiceTypeRuleMap = new HashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        userServiceTypeRuleMap = applicationContext.getBeansOfType(UserServiceTypeRule.class);
    }

    /**
     * 获取用户等级的访问处理类
     * @param userLevel
     * @return
     */
    private static Optional<UserServiceTypeRule> getUserServiceTypeRule(String userLevel,String serviceType){
        String key = userLevel + serviceType;
        UserServiceTypeRule userServiceTypeRule = userServiceTypeRuleMap.get(key);
        if(userServiceTypeRule==null){
            return Optional.empty();
        }
        return Optional.of(userServiceTypeRule);
    }

    /**
     * 判断当前用户是否可以访问 当前模型
     * @param userId
     * @param serviceType
     * @return
     */
    public static boolean access(Long userId,String serviceType){
        Optional<CacheUserInfoVo> userInfoVO = SessionUser.getUserInfoVO();
        if(!userInfoVO.isPresent()){
            log.info("access not login info userId={}",userId);
            return false;
        }
        // 获取 用户模型访问配置类
        UserLevelAccessVo userLevelAccessVo = getUserLevelAccessVo(userInfoVO.get(),serviceType);
        if(userLevelAccessVo==null){
            log.info("access user not have LevelAccess userId={},serviceType={}",userId,serviceType);
            return false;
        }
        Optional<UserServiceTypeRule> userServiceTypeRule = getUserServiceTypeRule(userInfoVO.get().getUserLevel(),serviceType);
        return userServiceTypeRule.map(rule->rule.rule(userInfoVO.get().getUserLevel(),userLevelAccessVo)).orElse(false);
    }

    private static UserLevelAccessVo getUserLevelAccessVo(CacheUserInfoVo cacheUserInfoVO, String serviceType) {
        Map<String, UserLevelAccessVo> userLevelAccessVoMap = cacheUserInfoVO.getUserLevelAccessVoMap();
        return userLevelAccessVoMap != null ? userLevelAccessVoMap.get(serviceType) : null;
    }


}
