package com.blue.cat.handler;

import com.alibaba.fastjson.JSONObject;
import com.blue.cat.bean.constants.CommonConstant;
import com.blue.cat.bean.entity.UserInfo;
import com.blue.cat.bean.req.UserInfoLoginReq;
import com.blue.cat.bean.req.UserInfoRegisterReq;
import com.blue.cat.bean.req.UserUpdateReq;
import com.blue.cat.bean.util.UserAccessRuleUtil;
import com.blue.cat.bean.util.UserInfoUtil;
import com.blue.cat.bean.vo.CacheUserInfoVo;
import com.blue.cat.bean.vo.UserLevelAccessVo;
import com.blue.cat.handler.access.UserAccessHandler;
import com.blue.cat.service.impl.UserAccessRuleServiceImpl;
import com.blue.cat.service.impl.UserInfoServiceImpl;
import com.blue.cat.utils.CacheUtil;
import com.blue.cat.utils.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Component
public class UserHandler {

    @Resource
    private UserInfoServiceImpl userInfoService;

    @Resource
    private UserAccessRuleServiceImpl ruleService;

    @Autowired
    private UserAccessHandler userAccessHandler;

    private UserInfo updateReqToEntity(UserUpdateReq updateReq){
        UserInfo entity = new UserInfo();
        entity.setId(updateReq.getUserId());
        entity.setPassword(updateReq.getPassword());
        entity.setUserLevel(updateReq.getUserLevel());
        entity.setUpdateTime(LocalDateTime.now());
        entity.setStatus(updateReq.getStatus());
        return entity;
    }

    /**
     * 更新用户信息
     * @param updateReq
     * @return
     */
    @Transactional
    public CacheUserInfoVo updateUserInfo(UserUpdateReq updateReq){
        UserInfo entity = updateReqToEntity(updateReq);
        boolean result = userInfoService.updateById(entity);
        if(result){
            UserInfo userInfo = userInfoService.getBaseMapper().selectById(entity.getId());
            return buildCacheUserInfoVo(userInfo);
        }
        return null;
    }


    /**
     * 用户登录
     * @param loginReq
     * @return
     */
    public CacheUserInfoVo login(UserInfoLoginReq loginReq){
        Optional<UserInfo> entity = userInfoService.queryUserByAccountAndPassword(loginReq.getAccount(), loginReq.getPassword());
        if(entity.isPresent()){
            UserInfo user = entity.get();
            CacheUserInfoVo userVo = buildCacheUserInfoVo(user);
            CacheUtil.put(String.valueOf(user.getId()), JSONObject.toJSONString(userVo));
            return userVo;
        }
        return null;
    }

    /**
     * 构建缓存实体
     * @param user
     */
    private CacheUserInfoVo buildCacheUserInfoVo(UserInfo user){
        CacheUserInfoVo cacheUserInfoVo = UserInfoUtil.entityToVo(user);
        Map<String, UserLevelAccessVo> accessVoMap = userAccessHandler.getUserAccessRule(user.getId());
        cacheUserInfoVo.setUserLevelAccessVoMap(accessVoMap);
        return cacheUserInfoVo;
    }

    /**
     * 用户注册
     * @param registerReq
     * @return
     */
    @Transactional
    public ResultVO register(UserInfoRegisterReq registerReq){
        String account = registerReq.getAccount();
        boolean existAccount = userInfoService.checkExistAccount(account);
        if(existAccount){
            return ResultVO.fail("该账号已经被注册过了！");
        }

        UserInfo userInfo = UserInfoUtil.reqToEntity(registerReq);
        int result = userInfoService.addUser(userInfo);
        if(result>0){
            ruleService.addAccess(UserAccessRuleUtil.getAccessRuleGpt3(userInfo.getId()));
            login(UserInfoLoginReq.builder().account(registerReq.getAccount()).password(registerReq.getPassword()).build());
            Map<String,String> reqMap = new HashedMap();
            reqMap.put(CommonConstant.TOKEN,String.valueOf(userInfo.getId()));
            return ResultVO.success(reqMap);
        }
        return ResultVO.fail("注册失败！服务器繁忙！");
    }

}
