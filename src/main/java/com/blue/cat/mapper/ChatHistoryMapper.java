package com.blue.cat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.blue.cat.bean.entity.ChatHistory;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author lixin
 * @since 2023-05-07
 */
public interface ChatHistoryMapper extends BaseMapper<ChatHistory> {

    @Delete("delete from chat_history where conversation_uid =#{conversationUid}")
    int deleteByconversationId(@Param("conversationUid") String conversationUid);

}
