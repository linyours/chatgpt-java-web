package com.blue.cat.bean.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserUpdateReq {

    /**
     * 用户id
     */
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    /**
     * 登录密码
     */

    private String password;

    /**
     * 账号状态
     * 0：状态 1：禁用
     */
    private Integer status;

    /**
     * 用户等级
     */
    private String userLevel;
}
