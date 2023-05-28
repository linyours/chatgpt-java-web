package com.blue.cat.bean.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author lixin
 * @since 2023-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_info")
public class UserInfo implements Serializable {

    private static final long serialVersionUID=1L;

      private Long id;

    /**
     * 用户昵称
     */
    private String username;

    /**
     * 微信授权的openId
     */
    private String openId;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 电话信息
     */
    private String phone;

    /**
     * 用户账号
     */
    private String account;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

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
