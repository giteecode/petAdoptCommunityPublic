package com.lian.pet.service.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lian.pet.domain.dto.AddCommentDTO;
import com.lian.pet.domain.entity.Comment;
import com.lian.pet.domain.entity.WxUser;
import com.lian.pet.domain.vo.CommentVO;
import com.lian.pet.factory.ResponseBeanFactory;
import com.lian.pet.mapper.CommentMapper;
import com.lian.pet.mapper.WxUserMapper;
import com.lian.pet.service.CommentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Desc:

 * @Time: 2022/2/2 20:46
 */
@Slf4j
@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentMapper commentMapper;
    private final WxUserMapper wxUserMapper;

    @Override
    public void addComment(AddCommentDTO req) {
        Comment comment = ResponseBeanFactory.getComment(req);
        commentMapper.insert(comment);
        log.info("执行成功[添加评论]");
    }

    @Override
    public List<CommentVO> queryCommentsByFromId(Integer fromId, String type) {
        List<Comment> comments = commentMapper.selectList(Wrappers.<Comment>lambdaQuery()
                .eq(Comment::getFromId, fromId)
                .eq(Comment::getType, type));
        if (comments.isEmpty()) {
            return null;
        }
        List<CommentVO> commentVOS = comments.stream().map(CommentVO::fromComment).collect(Collectors.toList());
        commentVOS.forEach(commentVO -> {
            WxUser wxUser = getWxUser(commentVO.getOwnerId());
            commentVO.setAvatarUrl(wxUser.getAvatarUrl());
            commentVO.setNickname(wxUser.getNickName());
            Comment comment = commentMapper.selectById(commentVO.getParentId());
            if (ObjectUtils.isNotEmpty(comment)) {
                commentVO.setParentName(getWxUser(comment.getOwnerId()).getNickName());
            }
        });
        log.info("执行成功[查询评论列表],fromId={}", fromId);
        return commentVOS;
    }

    private WxUser getWxUser(String ownerId) {
        return wxUserMapper.selectOne(Wrappers.<WxUser>lambdaQuery().eq(WxUser::getOpenId, ownerId));
    }

    @Override
    public Integer queryCount(Integer fromId, String type) {
        Integer count = commentMapper.selectCount(Wrappers.<Comment>lambdaQuery()
                .eq(Comment::getFromId, fromId)
                .eq(Comment::getType, type));
        log.info("执行成功[查询评论数量],fromId={}", fromId);
        return count;
    }

    @Override
    public void addLove(Integer id) {
        Comment comment = commentMapper.selectById(id);
        comment.setLove(comment.getLove() + 1);
        commentMapper.updateById(comment);
        log.info("执行成功[评论点赞],content={}", comment.getContent());
    }

    @Override
    public void cutLove(Integer id) {
        Comment comment = commentMapper.selectById(id);
        comment.setLove(comment.getLove() - 1);
        commentMapper.updateById(comment);
        log.info("执行成功[评论取消点赞],content={}", comment.getContent());
    }

    @Override
    public void deleteComment(Integer id) {
        // 删除该条评论下所有评论
        List<Integer> ids = new ArrayList<>();
        ids.add(id);
        List<Comment> comments = commentMapper.selectList(Wrappers.<Comment>lambdaQuery()
                .eq(Comment::getParentId, id)
                .or()
                .eq(Comment::getGrandId, id));
        if (!comments.isEmpty()) {
            comments.forEach(comment -> ids.add(comment.getId()));
        }
        commentMapper.deleteBatchIds(ids);
        log.info("执行成功[删除评论],ids={}", ids);
    }
}
