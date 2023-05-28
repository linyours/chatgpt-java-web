package com.blue.cat.handler.access.rule;


/**
 * 模型的匹配的规则
 * 不同模型 针对不同的身份的用户 的限制规则是不一样的
 */
public interface UserServiceTypeRule<T> {

    boolean rule(String userLevel,T accessVo);
}
