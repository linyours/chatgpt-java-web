package com.blue.cat.service.impl;

import com.blue.cat.bean.entity.UserAccessRule;
import com.blue.cat.bean.vo.UserLevelAccessVo;
import com.blue.cat.mapper.UserAccessRuleMapper;
import com.blue.cat.service.IUserAccessRuleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户访问规则 服务实现类
 * </p>
 *
 * @author lixin
 * @since 2023-05-10
 */
@Slf4j
@Service
public class UserAccessRuleServiceImpl extends ServiceImpl<UserAccessRuleMapper, UserAccessRule> implements IUserAccessRuleService {


    public List<UserLevelAccessVo> getAllAccessRuleByUserId(Long userId){

        List<UserAccessRule> userAccessRules = super.baseMapper.queryByUserId(userId);
        return userAccessRules.stream().map(this::entityToVo).collect(Collectors.toList());
    }

    /**
     * 默认添加默认访问规则配置
     * @return
     */
    public int addAccess(UserAccessRule accessRule){
        return super.baseMapper.insert(accessRule);
    }


    /**
     * 根据ids查询
     * @param ids
     * @return
     */
    public List<UserAccessRule> getByIds(Set<Long> ids){
        return this.baseMapper.queryByIds(ids);
    }

    /**
     * 实体转化为vo
     * @param accessRule
     * @return
     */
    private UserLevelAccessVo entityToVo(UserAccessRule accessRule){
        UserLevelAccessVo vo = new UserLevelAccessVo();
        vo.setId(accessRule.getId());
        vo.setUseNumber(new AtomicInteger(accessRule.getUseNumber()));
        vo.setServiceType(accessRule.getServiceType());
        vo.setStartEffectiveTime(accessRule.getStartEffectiveTime());
        vo.setEndEffectiveTime(accessRule.getEndEffectiveTime());
        return vo;
    }

    /**
     * 批量更新 用户访问配置
     * @param accessRules
     * @return
     */
    public boolean batchUpdate(List<UserAccessRule> accessRules){
        if(CollectionUtils.isEmpty(accessRules)){
            log.info("batchUpdate accessVos is empty");
            return false;
        }
        return this.updateBatchById(accessRules);
    }



    /**
     * vo转实体
     * @param vo
     * @return
     */
    private UserAccessRule voToEntity(UserLevelAccessVo vo){
        UserAccessRule entity = new UserAccessRule();
        entity.setId(vo.getId());
        entity.setStartEffectiveTime(vo.getStartEffectiveTime());
        entity.setEndEffectiveTime(vo.getEndEffectiveTime());
        entity.setUpdateUser("system_auto");
        entity.setUseNumber(vo.getUseNumber().get());
        return entity;
    }

}
