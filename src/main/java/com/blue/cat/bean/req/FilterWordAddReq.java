package com.blue.cat.bean.req;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class FilterWordAddReq {


    @NotNull
    private String wordContent;

    /**
     * 通道id信息，以逗号开头，逗号结尾：,12312,123212,
     */
    @NotNull
    private List<Long>channelIds;

    /**
     * 语言
     */
    @NotNull
    private String language;

    /**
     * 敏感词类型 1：风险词，2：url 3 全拼  4：白词
     */
    @NotNull
    private Integer type;

    /**
     * 风险等级
     */
    @NotNull
    private Integer riskType;

    /**
     * 风险等级
     */
    @NotNull
    private Integer riskLevel;

}
