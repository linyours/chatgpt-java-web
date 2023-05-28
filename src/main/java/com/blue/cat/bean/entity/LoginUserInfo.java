package com.blue.cat.bean.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户登录的id
 * </p>
 *
 * @author lixin
 * @since 2023-05-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("login_user_info")
public class LoginUserInfo implements Serializable {

    private static final long serialVersionUID=1L;

      private Long id;

    /**
     * session_key
     */
    private String sessionId;

    /**
     * 用户登录信息的json串
     */
    private String userInfo;


}
