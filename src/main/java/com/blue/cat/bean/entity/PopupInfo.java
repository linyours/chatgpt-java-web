package com.blue.cat.bean.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author linyous
 * @since 2023-05-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("popup_info")
public class PopupInfo implements Serializable {

    private static final long serialVersionUID=1L;

    private Long id;

    /**
     * 标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 公告内容-富文本
     */
    @Column(name = "content")
    private String content;

    /**
     * 创建时间
     */
    @Column(name = "createTime")
    private LocalDateTime createTime;
    /**
     * 创建时间
     */
    @Column(name = "popupLocation")
    private String popupLocation;

    /**
     * 创建时间
     */
    @Column(name = "isShow")
    private Integer isShow;


}
