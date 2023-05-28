package com.blue.cat.bean.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户访问规则
 * </p>
 *
 * @author lixin
 * @since 2023-05-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_access_rule")
public class UserAccessRule implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 用户id
     */
      private Long id;

    private Long userId;

    /**
     * 模型标识
     */
    private String serviceType;

    /**
     * 使用次数, 如果 = -2 代表不限制次数
     */
    private Integer useNumber;

    /**
     * 开始生效时间
     */
    private LocalDateTime startEffectiveTime;

    /**
     * 有效结束时间
     */
    private LocalDateTime endEffectiveTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    private String updateUser;


}
