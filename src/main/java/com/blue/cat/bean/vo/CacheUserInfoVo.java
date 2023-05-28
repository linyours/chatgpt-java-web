package com.blue.cat.bean.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Map;

@Data
public class CacheUserInfoVo {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    /**
     * 用户昵称
     */
    private String username;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户账号
     */
    private String account;


    /**
     * 账号状态 0正常 1：禁止登录
     */
    private Integer status;


    /**
     * 用户等级
     */
    private String userLevel;

    /**
     * 用户模型访问规则配置
     * key: 模型标识
     * value: 相应模型的规则
     */
    private Map<String,UserLevelAccessVo> userLevelAccessVoMap;
}
