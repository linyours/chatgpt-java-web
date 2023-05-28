package com.blue.cat.job;

import com.alibaba.fastjson.JSONObject;
import com.blue.cat.bean.entity.UserAccessRule;
import com.blue.cat.bean.vo.CacheUserInfoVo;
import com.blue.cat.bean.vo.UserLevelAccessVo;
import com.blue.cat.service.impl.UserAccessRuleServiceImpl;
import com.blue.cat.utils.CacheUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 更新用户访问规则
 */
@Slf4j
@Component
public class UserAccessJob {

    @Resource
    private UserAccessRuleServiceImpl ruleService;

    /**
     * 定时更新缓存中的 访问规则类
     */
    @Async
    @Scheduled(cron = "0 0/1 * * * ?")
    public void updateAccess(){
        long startTime = System.currentTimeMillis();
        log.info("updateAccess 开始执行");
        Collection<String> userInfoJsons = CacheUtil.getUserInfo();

        List<UserLevelAccessVo> accessVos = new ArrayList<>();
        for (String userInfoJson : userInfoJsons) {
            CacheUserInfoVo cacheUserInfoVo = JSONObject.parseObject(userInfoJson, CacheUserInfoVo.class);
            Map<String, UserLevelAccessVo> accessVoMap = cacheUserInfoVo.getUserLevelAccessVoMap();
            if(accessVoMap!=null && accessVoMap.size()>0){
                // todo 一次性更新所有登录用户的模型配置 这样到导致不需要更新的也会进行更新 待优化 等量大这里可能会有问题
                accessVos.addAll(accessVoMap.values());
            }
        }
        if(CollectionUtils.isEmpty(accessVos)){
            log.info("updateAccess not have update access rule");
            return;
        }
        List<UserAccessRule> needUpdateRuleList = getNeedUpdateRule(accessVos);
        boolean result = ruleService.batchUpdate(needUpdateRuleList);
        log.info("updateAccess 执行结束 result={} cost={}",result,System.currentTimeMillis()-startTime);
    }

    /**
     * 获取需要更新的规则类
     * @param accessVos
     * @return
     */
    private List<UserAccessRule> getNeedUpdateRule(List<UserLevelAccessVo> accessVos){

        //需要更新的访问配置
        List<UserAccessRule> needUpdateRuleList = new ArrayList<>();

        Map<Long, UserLevelAccessVo> accessVoMap = accessVos.stream()
                .collect(Collectors.toMap(UserLevelAccessVo::getId, Function.identity()));

        Set<Long> ids = accessVoMap.keySet();
        List<UserAccessRule> userAccessRules = ruleService.getByIds(ids);
        for (UserAccessRule entity : userAccessRules) {
            UserLevelAccessVo vo = accessVoMap.get(entity.getId());
            // 如果实体和缓存的调用次数不一致 则是需要更新到数据库
            if(entity.getUseNumber() != vo.getUseNumber().get()){
                entity.setUseNumber(vo.getUseNumber().get());
                entity.setUpdateUser("system_auto");
                entity.setUpdateTime(LocalDateTime.now());
                needUpdateRuleList.add(entity);
            }
        }
        return needUpdateRuleList;
    }
}
