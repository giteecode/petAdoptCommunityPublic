package com.ruoyi.adopt.service.impl;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.adopt.mapper.ChatMapper;
import com.ruoyi.adopt.domain.Chat;
import com.ruoyi.adopt.service.IChatService;

/**
 * 聊天Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-02-16
 */
@Service
public class ChatServiceImpl implements IChatService 
{
    @Autowired
    private ChatMapper chatMapper;

    /**
     * 查询聊天
     * 
     * @param id 聊天主键
     * @return 聊天
     */
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public Chat selectChatById(Long id)
    {
        return chatMapper.selectChatById(id);
    }

    /**
     * 查询聊天列表
     * 
     * @param chat 聊天
     * @return 聊天
     */
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public List<Chat> selectChatList(Chat chat)
    {
        return chatMapper.selectChatList(chat);
    }

    /**
     * 新增聊天
     * 
     * @param chat 聊天
     * @return 结果
     */
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public int insertChat(Chat chat)
    {
        chat.setCreateTime(DateUtils.getNowDate());
        return chatMapper.insertChat(chat);
    }

    /**
     * 修改聊天
     * 
     * @param chat 聊天
     * @return 结果
     */
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public int updateChat(Chat chat)
    {
        chat.setUpdateTime(DateUtils.getNowDate());
        return chatMapper.updateChat(chat);
    }

    /**
     * 批量删除聊天
     * 
     * @param ids 需要删除的聊天主键
     * @return 结果
     */
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public int deleteChatByIds(Long[] ids)
    {
        return chatMapper.deleteChatByIds(ids);
    }

    /**
     * 删除聊天信息
     * 
     * @param id 聊天主键
     * @return 结果
     */
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public int deleteChatById(Long id)
    {
        return chatMapper.deleteChatById(id);
    }
}
