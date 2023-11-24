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
import com.ruoyi.adopt.domain.PetCircle;
import com.ruoyi.adopt.service.IPetCircleService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 动态Controller
 * 
 * @author ruoyi
 * @date 2022-02-16
 */
@RestController
@RequestMapping("/adopt/circle")
public class PetCircleController extends BaseController
{
    @Autowired
    private IPetCircleService petCircleService;

    /**
     * 查询动态列表
     */
    @PreAuthorize("@ss.hasPermi('adopt:circle:list')")
    @GetMapping("/list")
    public TableDataInfo list(PetCircle petCircle)
    {
        startPage();
        List<PetCircle> list = petCircleService.selectPetCircleList(petCircle);
        return getDataTable(list);
    }

    /**
     * 导出动态列表
     */
    @PreAuthorize("@ss.hasPermi('adopt:circle:export')")
    @Log(title = "动态", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PetCircle petCircle)
    {
        List<PetCircle> list = petCircleService.selectPetCircleList(petCircle);
        ExcelUtil<PetCircle> util = new ExcelUtil<PetCircle>(PetCircle.class);
        util.exportExcel(response, list, "动态数据");
    }

    /**
     * 获取动态详细信息
     */
    @PreAuthorize("@ss.hasPermi('adopt:circle:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(petCircleService.selectPetCircleById(id));
    }

    /**
     * 新增动态
     */
    @PreAuthorize("@ss.hasPermi('adopt:circle:add')")
    @Log(title = "动态", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PetCircle petCircle)
    {
        return toAjax(petCircleService.insertPetCircle(petCircle));
    }

    /**
     * 修改动态
     */
    @PreAuthorize("@ss.hasPermi('adopt:circle:edit')")
    @Log(title = "动态", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PetCircle petCircle)
    {
        return toAjax(petCircleService.updatePetCircle(petCircle));
    }

    /**
     * 删除动态
     */
    @PreAuthorize("@ss.hasPermi('adopt:circle:remove')")
    @Log(title = "动态", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(petCircleService.deletePetCircleByIds(ids));
    }
}
