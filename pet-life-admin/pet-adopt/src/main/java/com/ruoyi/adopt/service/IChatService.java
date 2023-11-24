package com.ruoyi.adopt.service;

import java.util.List;
import com.ruoyi.adopt.domain.Chat;

/**
 * 聊天Service接口
 * 
 * @author ruoyi
 * @date 2022-02-16
 */
public interface IChatService 
{
    /**
     * 查询聊天
     * 
     * @param id 聊天主键
     * @return 聊天
     */
    public Chat selectChatById(Long id);

    /**
     * 查询聊天列表
     * 
     * @param chat 聊天
     * @return 聊天集合
     */
    public List<Chat> selectChatList(Chat chat);

    /**
     * 新增聊天
     * 
     * @param chat 聊天
     * @return 结果
     */
    public int insertChat(Chat chat);

    /**
     * 修改聊天
     * 
     * @param chat 聊天
     * @return 结果
     */
    public int updateChat(Chat chat);

    /**
     * 批量删除聊天
     * 
     * @param ids 需要删除的聊天主键集合
     * @return 结果
     */
    public int deleteChatByIds(Long[] ids);

    /**
     * 删除聊天信息
     * 
     * @param id 聊天主键
     * @return 结果
     */
    public int deleteChatById(Long id);
}
