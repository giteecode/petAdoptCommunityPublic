package com.lian.pet.controller;

import com.lian.pet.common.basic.response.AppResp;
import com.lian.pet.domain.dto.QueryArticleDTO;
import com.lian.pet.domain.vo.ArticleVO;
import com.lian.pet.service.ArticleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @Desc:

 * @Time: 2022/2/3 16:23
 */
@Slf4j
@RequestMapping("article")
@RestController
@AllArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    /**
     * 查询文章列表
     * @param req
     * @return
     */
    @PostMapping("/queryArticles")
    public AppResp<List<ArticleVO>> queryArticles(@RequestBody QueryArticleDTO req) {
        return AppResp.succeed(articleService.queryArticles(req));
    }
}
