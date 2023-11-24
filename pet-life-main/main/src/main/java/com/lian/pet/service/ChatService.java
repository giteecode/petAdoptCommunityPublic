package com.lian.pet.service;

import com.lian.pet.domain.dto.AddChatDTO;
import com.lian.pet.domain.vo.ChatCountVO;
import com.lian.pet.domain.vo.ChatVO;
import java.util.List;

/**
 * @Desc: Chat Service

 * @Time: 2022/2/9 9:48
 */
public interface ChatService {

    /**
     * 新增消息
     * @param req
     * @return
     */
    ChatVO addChat(AddChatDTO req);

    /**
     * 查询消息数量
     * @param userId
     * @return
     */
    List<ChatCountVO> queryChatCount(String userId);

    /**
     * 查询聊天内容
     * @param userId1
     * @param userId2
     * @return
     */
    List<ChatVO> queryChatContent(String userId1, String userId2);

    /**
     * 查询未读消息总数
     * @param userId
     * @return
     */
    Integer queryUnreadTotal(String userId);
}
