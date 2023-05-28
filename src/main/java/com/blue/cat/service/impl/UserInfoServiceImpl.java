package com.blue.cat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blue.cat.bean.entity.UserInfo;
import com.blue.cat.mapper.UserInfoMapper;
import com.blue.cat.service.IUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lixin
 * @since 2023-05-07
 */
@Slf4j
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

    /**
     * 根据账号和密码查询用户信息
     * @param account
     * @param password
     * @return
     */
    public Optional<UserInfo> queryUserByAccountAndPassword(String account, String password) {
        UserInfo userInfo = super.baseMapper.queryAccountAndPassword(account, password);
        log.info("login account={},userInfo={}",account,userInfo);
        if(userInfo==null){
            return Optional.empty();
        }
        return Optional.of(userInfo);
    }

    /**
     * 检测账号是否已经存在了
     * @param account
     * @return
     */
    public boolean checkExistAccount(String account){
        UserInfo userInfo = super.baseMapper.queryByAccount(account);
        log.info("checkExistAccount userInfo={}",userInfo);
        return userInfo!=null;
    }

    /**
     * 添加用户
     * @param userInfo
     * @return
     */
    public int addUser(UserInfo userInfo){
        return this.baseMapper.insert(userInfo);
    }
}
