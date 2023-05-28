package com.blue.cat.bean.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@FieldNameConstants
@Data
@TableName("Conversation")
public class Conversation implements Serializable {

    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    /**
     * 会话uuid
     * uuid
     */
    @Column(name = "conversation_uid")
    private String conversationUid;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private String userId;

    @Column(name = "name")
    private String name;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;
}
