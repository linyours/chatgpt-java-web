package com.blue.cat.bean.constants;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum LimitEnum {

    IP(CommonConstant.LIMIT_IP,5,86400L);

    private String limit;//规则标识
    private Integer number;// 次数
    private Long time;// 时间 单位自定义
}
