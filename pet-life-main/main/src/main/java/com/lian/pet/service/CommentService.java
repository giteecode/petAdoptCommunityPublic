package com.lian.pet.service;

import com.lian.pet.domain.dto.AddCommentDTO;
import com.lian.pet.domain.vo.CommentVO;
import java.util.List;

/**
 * @Desc: Comment Service

 * @Time: 2022/2/2 20:03
 */
public interface CommentService {

    /**
     * 新增评论
     * @param req
     */
    void addComment(AddCommentDTO req);

    /**
     * 查询评论
     * @param fromId
     * @param type
     * @return
     */
    List<CommentVO> queryCommentsByFromId(Integer fromId, String type);

    /**
     * 查询评论总数
     * @param fromId
     * @param type
     * @return
     */
    Integer queryCount(Integer fromId, String type);

    /**
     * 点赞
     * @param id
     */
    void addLove(Integer id);

    /**
     * 取消点赞
     * @param id
     */
    void cutLove(Integer id);

    /**
     * 删除评论
     * @param id
     */
    void deleteComment(Integer id);
}
