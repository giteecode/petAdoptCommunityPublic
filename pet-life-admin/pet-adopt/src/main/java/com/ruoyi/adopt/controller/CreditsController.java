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
import com.ruoyi.adopt.domain.Credits;
import com.ruoyi.adopt.service.ICreditsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 积分Controller
 * 
 * @author ruoyi
 * @date 2022-02-16
 */
@RestController
@RequestMapping("/adopt/credits")
public class CreditsController extends BaseController
{
    @Autowired
    private ICreditsService creditsService;

    /**
     * 查询积分列表
     */
    @PreAuthorize("@ss.hasPermi('adopt:credits:list')")
    @GetMapping("/list")
    public TableDataInfo list(Credits credits)
    {
        startPage();
        List<Credits> list = creditsService.selectCreditsList(credits);
        return getDataTable(list);
    }

    /**
     * 导出积分列表
     */
    @PreAuthorize("@ss.hasPermi('adopt:credits:export')")
    @Log(title = "积分", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Credits credits)
    {
        List<Credits> list = creditsService.selectCreditsList(credits);
        ExcelUtil<Credits> util = new ExcelUtil<Credits>(Credits.class);
        util.exportExcel(response, list, "积分数据");
    }

    /**
     * 获取积分详细信息
     */
    @PreAuthorize("@ss.hasPermi('adopt:credits:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(creditsService.selectCreditsById(id));
    }

    /**
     * 新增积分
     */
    @PreAuthorize("@ss.hasPermi('adopt:credits:add')")
    @Log(title = "积分", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Credits credits)
    {
        return toAjax(creditsService.insertCredits(credits));
    }

    /**
     * 修改积分
     */
    @PreAuthorize("@ss.hasPermi('adopt:credits:edit')")
    @Log(title = "积分", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Credits credits)
    {
        return toAjax(creditsService.updateCredits(credits));
    }

    /**
     * 删除积分
     */
    @PreAuthorize("@ss.hasPermi('adopt:credits:remove')")
    @Log(title = "积分", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(creditsService.deleteCreditsByIds(ids));
    }
}
