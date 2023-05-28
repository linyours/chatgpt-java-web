package com.blue.cat.bean.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@FieldNameConstants
@Data
@TableName("chat_history")
public class ChatHistory implements Serializable {

    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    /**
     * 发送人
     */
    @Column(name = "from_user_id")
    private String fromUserId;

    /**
     * 接收人
     */
    @Column(name = "to_user_id")
    private String toUserId;

    /**
     * 消息来源
     * 1，用户；2，机器人
     */
    @Column(name = "source")
    private Integer source;

    @Column(name = "conversation_uid")
    private String conversationUid;

    /**
     * 聊天内容
     */
    @Column(name = "content")
    private String content;

    /**
     * 发送时间
     */
    @Column(name = "publish_time")
    private Long publishTime;

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
