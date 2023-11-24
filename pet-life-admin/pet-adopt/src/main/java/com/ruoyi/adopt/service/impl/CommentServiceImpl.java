package com.ruoyi.adopt.service.impl;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.adopt.mapper.CommentMapper;
import com.ruoyi.adopt.domain.Comment;
import com.ruoyi.adopt.service.ICommentService;

/**
 * 评论Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-02-16
 */
@Service
public class CommentServiceImpl implements ICommentService 
{
    @Autowired
    private CommentMapper commentMapper;

    /**
     * 查询评论
     * 
     * @param id 评论主键
     * @return 评论
     */
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public Comment selectCommentById(Long id)
    {
        return commentMapper.selectCommentById(id);
    }

    /**
     * 查询评论列表
     * 
     * @param comment 评论
     * @return 评论
     */
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public List<Comment> selectCommentList(Comment comment)
    {
        return commentMapper.selectCommentList(comment);
    }

    /**
     * 新增评论
     * 
     * @param comment 评论
     * @return 结果
     */
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public int insertComment(Comment comment)
    {
        comment.setCreateTime(DateUtils.getNowDate());
        return commentMapper.insertComment(comment);
    }

    /**
     * 修改评论
     * 
     * @param comment 评论
     * @return 结果
     */
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public int updateComment(Comment comment)
    {
        comment.setUpdateTime(DateUtils.getNowDate());
        return commentMapper.updateComment(comment);
    }

    /**
     * 批量删除评论
     * 
     * @param ids 需要删除的评论主键
     * @return 结果
     */
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public int deleteCommentByIds(Long[] ids)
    {
        return commentMapper.deleteCommentByIds(ids);
    }

    /**
     * 删除评论信息
     * 
     * @param id 评论主键
     * @return 结果
     */
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public int deleteCommentById(Long id)
    {
        return commentMapper.deleteCommentById(id);
    }
}
