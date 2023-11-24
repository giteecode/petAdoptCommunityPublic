package com.lian.pet.controller;

import com.lian.pet.common.basic.response.AppResp;
import com.lian.pet.domain.dto.AddCommentDTO;
import com.lian.pet.domain.vo.CommentVO;
import com.lian.pet.service.CommentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @Desc: Comment Controller

 * @Time: 2022/2/2 21:07
 */
@Slf4j
@RequestMapping("comment")
@RestController
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;

    /**
     * 新增评论
     * @param req
     * @return
     */
    @PostMapping("addComment")
    public AppResp<Integer> addComment(@RequestBody AddCommentDTO req) {
        commentService.addComment(req);
        return AppResp.succeed(1);
    }

    /**
     * 查询评论列表 By FromId
     * @param fromId
     * @param type
     * @return
     */
    @GetMapping("queryComments")
    public AppResp<List<CommentVO>> queryCommentsByFromId(@RequestParam("fromId") Integer fromId,
                                                          @RequestParam("type") String type) {
        return AppResp.succeed(commentService.queryCommentsByFromId(fromId, type));
    }

    /**
     * 查询评论数 By FromId
     * @param fromId
     * @param type
     * @return
     */
    @GetMapping("queryCount")
    public AppResp<Integer> queryCount(@RequestParam("fromId") Integer fromId,
                                       @RequestParam("type") String type) {
        return AppResp.succeed(commentService.queryCount(fromId, type));
    }

    /**
     * 点赞
     * @param id
     * @return
     */
    @GetMapping("addLove")
    public AppResp<Integer> addLove(@RequestParam("id") Integer id) {
        commentService.addLove(id);
        return AppResp.succeed(1);
    }

    /**
     * 取消点赞
     * @param id
     * @return
     */
    @GetMapping("cutLove")
    public AppResp<Integer> cutLove(@RequestParam("id") Integer id) {
        commentService.cutLove(id);
        return AppResp.succeed(1);
    }

    /**
     * 删除评论（逻辑删除）
     * @param id
     * @return
     */
    @GetMapping("deleteComment")
    public AppResp<Integer> deleteComment(@RequestParam("id") Integer id) {
        commentService.deleteComment(id);
        return AppResp.succeed(1);
    }

}
