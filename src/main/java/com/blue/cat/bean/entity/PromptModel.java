package com.blue.cat.bean.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("prompt_model")
public class PromptModel implements Serializable {

    private static final long serialVersionUID=1L;

    private Long id;

    /**
     * 类型：效率工具、生活、娱乐、学习
     */
    @Column(name = "type")
    private String type;

    /**
     * 标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 简介
     */
    @Column(name = "introduce")
    private String introduce;

    /**
     * 示例
     */
    @Column(name = "demo")
    private String demo;

    /**
     * 内容
     */
    @Column(name = "content")
    private String content;

    /**
     * 0，无效；1，有效
     */
    @Column(name = "state")
    private Integer state;

    /**
     * 排序值
     */
    @Column(name = "sort")
    private Integer sort;

    /**
     * 创建时间
     */
    @Column(name = "createTime")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "updateTime")
    private Date updateTime;
}
