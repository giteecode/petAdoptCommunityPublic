package com.lian.pet.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lian.pet.domain.dto.QueryArticleDTO;
import com.lian.pet.domain.entity.Article;
import com.lian.pet.domain.vo.ArticleVO;
import com.lian.pet.mapper.ArticleMapper;
import com.lian.pet.service.ArticleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Desc:

 * @Time: 2022/2/3 16:19
 */
@Slf4j
@Service
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleMapper articleMapper;

    @Override
    public List<ArticleVO> queryArticles(QueryArticleDTO req) {
        IPage<Article> page = new Page<>(req.getPageNum(), req.getPageSize());
        IPage<Article> iPage = articleMapper.selectPage(page, Wrappers.<Article>lambdaQuery()
                .groupBy(StringUtils.isBlank(req.getLabel()), Article::getLabel)
                .eq(StringUtils.isNotBlank(req.getLabel()), Article::getLabel, req.getLabel())
                .orderByDesc(Article::getUpdateTime));
        List<ArticleVO> articleVOS = iPage.getRecords().stream()
                .map(ArticleVO::fromArticle).collect(Collectors.toList());
        log.info("执行成功[查询文章列表]");
        return articleVOS;
    }
}
