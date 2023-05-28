package com.blue.cat.bean.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class UserLevelAccessVo {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 访问次数
     */
    private volatile AtomicInteger useNumber;

    /**
     * 模型标识
     */
    private String serviceType;

    /**
     * 开始生效时间
     */
    private volatile LocalDateTime startEffectiveTime;

    /**
     * 有效结束时间
     */
    private volatile LocalDateTime endEffectiveTime;

    /**
     * 上一次访问时间
     */
    private volatile LocalDateTime lastVisitDate;
}
