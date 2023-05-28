package com.blue.cat.bean.util;

import com.blue.cat.bean.entity.UserInfo;
import com.blue.cat.bean.req.UserInfoRegisterReq;
import com.blue.cat.bean.vo.CacheUserInfoVo;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;

public class UserInfoUtil {


    /**
     * 注册类实体转化为实体bean
     * @param registerReq
     * @return
     */
    public static UserInfo reqToEntity(UserInfoRegisterReq registerReq){
        UserInfo userInfo = new UserInfo();
        if(StringUtils.isNotBlank(registerReq.getAvatar())){
            userInfo.setAvatar(registerReq.getAvatar());
        }
        userInfo.setAccount(registerReq.getAccount());
        userInfo.setPassword(registerReq.getPassword());
        if(StringUtils.isNotEmpty(registerReq.getUsername())){
            userInfo.setUsername(registerReq.getUsername());
        }
        userInfo.setCreateTime(LocalDateTime.now());
        return userInfo;
    }

    /**
     * 用户实体转化为vo
     * @param userInfo
     * @return
     */
    public static CacheUserInfoVo entityToVo(UserInfo userInfo){
        CacheUserInfoVo vo = new CacheUserInfoVo();
        if(userInfo.getId()!=null){
            vo.setId(userInfo.getId());
        }
        if(StringUtils.isNotBlank(userInfo.getAccount())){
            vo.setAccount(userInfo.getAccount());
        }
        if(StringUtils.isNotBlank(userInfo.getAvatar())){
            vo.setAvatar(userInfo.getAvatar());
        }
        if(StringUtils.isNotBlank(userInfo.getUsername())){
            vo.setUsername(userInfo.getUsername());
        }
        if(userInfo.getStatus()!=null){
            vo.setStatus(userInfo.getStatus());
        }
        if(StringUtils.isNotBlank(userInfo.getUserLevel())){
            vo.setUserLevel(userInfo.getUserLevel());
        }
        return vo;
    }
}
