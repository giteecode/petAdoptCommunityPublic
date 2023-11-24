package com.ruoyi.adopt.service.impl;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.adopt.mapper.ArticleMapper;
import com.ruoyi.adopt.domain.Article;
import com.ruoyi.adopt.service.IArticleService;

/**
 * 文章Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-02-16
 */
@Service
public class ArticleServiceImpl implements IArticleService 
{
    @Autowired
    private ArticleMapper articleMapper;

    /**
     * 查询文章
     * 
     * @param id 文章主键
     * @return 文章
     */
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public Article selectArticleById(Long id)
    {
        return articleMapper.selectArticleById(id);
    }

    /**
     * 查询文章列表
     * 
     * @param article 文章
     * @return 文章
     */
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public List<Article> selectArticleList(Article article)
    {
        return articleMapper.selectArticleList(article);
    }

    /**
     * 新增文章
     * 
     * @param article 文章
     * @return 结果
     */
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public int insertArticle(Article article)
    {
        article.setCreateTime(DateUtils.getNowDate());
        return articleMapper.insertArticle(article);
    }

    /**
     * 修改文章
     * 
     * @param article 文章
     * @return 结果
     */
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public int updateArticle(Article article)
    {
        article.setUpdateTime(DateUtils.getNowDate());
        return articleMapper.updateArticle(article);
    }

    /**
     * 批量删除文章
     * 
     * @param ids 需要删除的文章主键
     * @return 结果
     */
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public int deleteArticleByIds(Long[] ids)
    {
        return articleMapper.deleteArticleByIds(ids);
    }

    /**
     * 删除文章信息
     * 
     * @param id 文章主键
     * @return 结果
     */
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public int deleteArticleById(Long id)
    {
        return articleMapper.deleteArticleById(id);
    }
}
