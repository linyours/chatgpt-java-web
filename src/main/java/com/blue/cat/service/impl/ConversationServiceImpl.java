package com.blue.cat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blue.cat.bean.entity.Conversation;
import com.blue.cat.mapper.ConversationMapper;
import com.blue.cat.service.IConversationService;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author huyd
 * @desc
 * @date 2023/5/7
 */
@Service
public class ConversationServiceImpl extends ServiceImpl<ConversationMapper, Conversation> implements IConversationService {

    private static final String KEY_CURRENT_CONVERSATION_CACHE = "USER_CURRENT_CONVERSATION";

    private final Cache<String, Map<String, String>> userCurrentConversationCache = CacheBuilder.newBuilder().build();

    /**
     * 保存用户当前所在会话uid
     * //todo 登录时将用户最新的会话插入缓存,或者用户手动切换会话时插入缓存
     */
    public void addUserCurrentConversation(String userId, String conversationUid) {
        Map<String, String> map = new HashMap<>();
        map.put(userId, conversationUid);
        userCurrentConversationCache.put(KEY_CURRENT_CONVERSATION_CACHE, map);
    }

    /**
     * 获取用户当前的会话uid
     *
     * @return
     */
    public Map<String, String> getUserCurrentConversation() {
        return userCurrentConversationCache.getIfPresent(KEY_CURRENT_CONVERSATION_CACHE);
    }

    /**
     * 获取所有会话
     *
     * @return
     */
    public List<Conversation> getAll() {
        QueryWrapper<Conversation> wrapper = new QueryWrapper<>();
        return baseMapper.selectList(wrapper);
    }

    /**
     * 删除某个会话
     *
     * @param conversationUid
     * @return
     */
    public int delByUid(String conversationUid) {
        return baseMapper.deleteByUid(conversationUid);
    }

    /**
     * 新增会话
     *
     * @param userId
     * @param name
     * @return
     */
    public int add(String userId, String name) {
        String uid = UUID.randomUUID().toString().replaceAll("-", "");
        Conversation conversation = new Conversation();
        conversation.setUserId(userId);
        conversation.setName(name);
        conversation.setConversationUid(uid);
        return baseMapper.insert(conversation);
    }

    /**
     * 修改会话名称
     *
     * @param uid
     * @param name
     * @return
     */
    public int updateName(String uid, String name) {
        Conversation conversation = new Conversation();
        conversation.setName(name);
        conversation.setConversationUid(uid);
        return baseMapper.insert(conversation);
    }
}
