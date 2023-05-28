package com.blue.cat.bean.req;


import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户查询请求实体
 */
@Data
public class UserQueryReq {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 账号
     */
    private String account;

    /**
     * 搜索开始时间
     */
    private LocalDateTime startTime;

    /**
     * 搜索结束时间
     */
    private LocalDateTime endTime;
}
