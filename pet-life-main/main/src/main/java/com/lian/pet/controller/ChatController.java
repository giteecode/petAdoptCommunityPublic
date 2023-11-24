package com.lian.pet.controller;

import com.lian.pet.common.basic.response.AppResp;
import com.lian.pet.domain.dto.AddChatDTO;
import com.lian.pet.domain.vo.ChatCountVO;
import com.lian.pet.domain.vo.ChatVO;
import com.lian.pet.service.ChatService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @Desc: Chat Controller

 * @Time: 2022/2/9 10:28
 */
@Slf4j
@RequestMapping("chat")
@RestController
@AllArgsConstructor
public class ChatController {
    private final ChatService chatService;

    /**
     * 新增消息
     * @param req
     * @return
     */
    @PostMapping("add")
    public AppResp<ChatVO> addChat(@RequestBody AddChatDTO req) {
        return AppResp.succeed(chatService.addChat(req));
    }

    /**
     * 查询消息数量
     * @param userId
     * @return
     */
    @GetMapping("queryCount")
    public AppResp<List<ChatCountVO>> queryCount(@RequestParam("userId") String userId) {
        return AppResp.succeed(chatService.queryChatCount(userId));
    }

    /**
     * 查询聊天内容
     * @param userId1
     * @param userId2
     * @return
     */
    @GetMapping("queryChatContent")
    public AppResp<List<ChatVO>> queryChatContent(@RequestParam("userId1") String userId1,
                                                  @RequestParam("userId2") String userId2) {
        return AppResp.succeed(chatService.queryChatContent(userId1, userId2));
    }

    /**
     * 查询未读消息总数
     * @param userId
     * @return
     */
    @GetMapping("queryUnreadTotal")
    public AppResp<Integer> queryUnreadTotal(@RequestParam("userId") String userId) {
        return AppResp.succeed(chatService.queryUnreadTotal(userId));
    }
}
