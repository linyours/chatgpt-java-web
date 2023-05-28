package com.blue.cat.access;

import com.blue.cat.RunnerTest;
import com.blue.cat.bean.entity.UserAccessRule;
import com.blue.cat.bean.util.UserAccessRuleUtil;
import com.blue.cat.mapper.UserAccessRuleMapper;
import com.blue.cat.service.impl.UserAccessRuleServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class AccessTest extends RunnerTest {

    @Autowired
    private UserAccessRuleServiceImpl accessRuleService;

    @Resource
    private UserAccessRuleMapper ruleMapper;

    @Test
    public void addAccess(){
        int i = accessRuleService.addAccess(UserAccessRuleUtil.getAccessRuleGpt3(1234L));
        System.out.println(i);
    }


    @Test
    public void queryIds(){
        List<UserAccessRule> userAccessRules = ruleMapper.queryByIds(new HashSet<>(Arrays.asList(1656217949066317826L)));
        System.out.println(userAccessRules);
    }

}
