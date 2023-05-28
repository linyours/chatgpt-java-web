package com.blue.cat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blue.cat.bean.entity.ChatHistory;
import com.blue.cat.mapper.ChatHistoryMapper;
import com.blue.cat.service.IChatHistoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author huyd
 * @desc
 * @date 2023/5/7
 */
@Service
public class ChatHistoryServiceImpl extends ServiceImpl<ChatHistoryMapper, ChatHistory> implements IChatHistoryService {

    public IPage<ChatHistory> getPage(String conversationUid, int pageNo, int pageSize) {
        if (StringUtils.isBlank(conversationUid)) {
            return new Page<>();
        }

        QueryWrapper<ChatHistory> wrapper = new QueryWrapper<>();
        wrapper.eq("conversation_uid", conversationUid);
        wrapper.orderByDesc("publish_time");
        IPage<ChatHistory> page = new Page<>();
        page.setCurrent(pageNo);
        page.setSize(pageSize);
        return baseMapper.selectPage(page, wrapper);
    }

    public int addChatHistory(ChatHistory chatHistory) {
        return this.baseMapper.insert(chatHistory);
    }

    public int delByConversationId(String conversationUid) {
        if (StringUtils.isBlank(conversationUid)) {
            return 0;
        }
        return this.baseMapper.deleteByconversationId(conversationUid);
    }
}
