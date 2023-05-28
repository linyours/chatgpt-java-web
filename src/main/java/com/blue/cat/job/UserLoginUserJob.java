package com.blue.cat.job;

import com.blue.cat.bean.entity.LoginUserInfo;
import com.blue.cat.service.impl.LoginUserInfoServiceImpl;
import com.blue.cat.utils.CacheUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

@Slf4j
@Component
public class UserLoginUserJob {

    @Autowired
    private LoginUserInfoServiceImpl loginUserInfoService;

    @Async
    @Scheduled(cron = "0 0/10 * * * ?")
    public void buildLoginUserInfo(){
        loginUserInfoService.buildLogInfoUser();
    }

    /**
     * 重新将登录的账号重新加入缓存中
     */
    @PostConstruct
    public void reCacheLoginUser(){
        long startTime = System.currentTimeMillis();
        List<LoginUserInfo> loginUserInfos = loginUserInfoService.getBaseMapper().getAll();
        if(CollectionUtils.isNotEmpty(loginUserInfos)){
            for (LoginUserInfo loginUserInfo : loginUserInfos) {
                CacheUtil.put(loginUserInfo.getSessionId(),loginUserInfo.getUserInfo());
            }
        }
        log.info("reCacheLoginUser end cacheSize={} cost={}",loginUserInfos.size(),System.currentTimeMillis()-startTime);
    }

}
