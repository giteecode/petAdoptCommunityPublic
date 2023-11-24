package com.ruoyi.adopt.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.adopt.domain.Article;
import com.ruoyi.adopt.service.IArticleService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 文章Controller
 * 
 * @author ruoyi
 * @date 2022-02-16
 */
@RestController
@RequestMapping("/adopt/article")
public class ArticleController extends BaseController
{
    @Autowired
    private IArticleService articleService;

    /**
     * 查询文章列表
     */
    @PreAuthorize("@ss.hasPermi('adopt:article:list')")
    @GetMapping("/list")
    public TableDataInfo list(Article article)
    {
        startPage();
        List<Article> list = articleService.selectArticleList(article);
        return getDataTable(list);
    }

    /**
     * 导出文章列表
     */
    @PreAuthorize("@ss.hasPermi('adopt:article:export')")
    @Log(title = "文章", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Article article)
    {
        List<Article> list = articleService.selectArticleList(article);
        ExcelUtil<Article> util = new ExcelUtil<Article>(Article.class);
        util.exportExcel(response, list, "文章数据");
    }

    /**
     * 获取文章详细信息
     */
    @PreAuthorize("@ss.hasPermi('adopt:article:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(articleService.selectArticleById(id));
    }

    /**
     * 新增文章
     */
    @PreAuthorize("@ss.hasPermi('adopt:article:add')")
    @Log(title = "文章", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Article article)
    {
        return toAjax(articleService.insertArticle(article));
    }

    /**
     * 修改文章
     */
    @PreAuthorize("@ss.hasPermi('adopt:article:edit')")
    @Log(title = "文章", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Article article)
    {
        return toAjax(articleService.updateArticle(article));
    }

    /**
     * 删除文章
     */
    @PreAuthorize("@ss.hasPermi('adopt:article:remove')")
    @Log(title = "文章", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(articleService.deleteArticleByIds(ids));
    }
}
