package com.lian.pet.service;

import com.lian.pet.domain.dto.QueryArticleDTO;
import com.lian.pet.domain.vo.ArticleVO;
import java.util.List;

/**
 * @Desc: Article Service

 * @Time: 2022/2/3 16:18
 */
public interface ArticleService {

    /**
     * 查询文章列表
     * @param req
     * @return
     */
    List<ArticleVO> queryArticles(QueryArticleDTO req);
}
