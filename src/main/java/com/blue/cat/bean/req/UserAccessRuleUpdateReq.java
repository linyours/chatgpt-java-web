package com.blue.cat.bean.req;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class UserAccessRuleUpdateReq {

    @NotNull(message = "规则ID不能为空")
    private Long id;

    /**
     * 使用次数, 如果 = -2 代表不限制次数
     */
    private Integer useNumber;

    /**
     * 本次更新的用户id
     */
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    /**
     * 开始生效时间
     */
    private LocalDateTime startEffectiveTime;

    /**
     * 有效结束时间
     */
    private LocalDateTime endEffectiveTime;
}
