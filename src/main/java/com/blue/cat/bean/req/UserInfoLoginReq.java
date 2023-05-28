package com.blue.cat.bean.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

/**
 * 用户登录
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserInfoLoginReq {
    /**
     * 账号
     */
    @NotNull(message = "账号不能为空")
    private String account;
    /**
     * 密码
     */
    @NotNull(message = "密码不能为空")
    private String password;
}
