package com.ruoyi.adopt.service;

import java.util.List;
import com.ruoyi.adopt.domain.Article;

/**
 * 文章Service接口
 * 
 * @author ruoyi
 * @date 2022-02-16
 */
public interface IArticleService 
{
    /**
     * 查询文章
     * 
     * @param id 文章主键
     * @return 文章
     */
    public Article selectArticleById(Long id);

    /**
     * 查询文章列表
     * 
     * @param article 文章
     * @return 文章集合
     */
    public List<Article> selectArticleList(Article article);

    /**
     * 新增文章
     * 
     * @param article 文章
     * @return 结果
     */
    public int insertArticle(Article article);

    /**
     * 修改文章
     * 
     * @param article 文章
     * @return 结果
     */
    public int updateArticle(Article article);

    /**
     * 批量删除文章
     * 
     * @param ids 需要删除的文章主键集合
     * @return 结果
     */
    public int deleteArticleByIds(Long[] ids);

    /**
     * 删除文章信息
     * 
     * @param id 文章主键
     * @return 结果
     */
    public int deleteArticleById(Long id);
}
