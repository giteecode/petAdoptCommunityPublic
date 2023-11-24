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
import com.ruoyi.adopt.domain.PetFind;
import com.ruoyi.adopt.service.IPetFindService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 寻宠Controller
 * 
 * @author ruoyi
 * @date 2022-02-16
 */
@RestController
@RequestMapping("/adopt/find")
public class PetFindController extends BaseController
{
    @Autowired
    private IPetFindService petFindService;

    /**
     * 查询寻宠列表
     */
    @PreAuthorize("@ss.hasPermi('adopt:find:list')")
    @GetMapping("/list")
    public TableDataInfo list(PetFind petFind)
    {
        startPage();
        List<PetFind> list = petFindService.selectPetFindList(petFind);
        return getDataTable(list);
    }

    /**
     * 导出寻宠列表
     */
    @PreAuthorize("@ss.hasPermi('adopt:find:export')")
    @Log(title = "寻宠", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PetFind petFind)
    {
        List<PetFind> list = petFindService.selectPetFindList(petFind);
        ExcelUtil<PetFind> util = new ExcelUtil<PetFind>(PetFind.class);
        util.exportExcel(response, list, "寻宠数据");
    }

    /**
     * 获取寻宠详细信息
     */
    @PreAuthorize("@ss.hasPermi('adopt:find:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(petFindService.selectPetFindById(id));
    }

    /**
     * 新增寻宠
     */
    @PreAuthorize("@ss.hasPermi('adopt:find:add')")
    @Log(title = "寻宠", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PetFind petFind)
    {
        return toAjax(petFindService.insertPetFind(petFind));
    }

    /**
     * 修改寻宠
     */
    @PreAuthorize("@ss.hasPermi('adopt:find:edit')")
    @Log(title = "寻宠", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PetFind petFind)
    {
        return toAjax(petFindService.updatePetFind(petFind));
    }

    /**
     * 删除寻宠
     */
    @PreAuthorize("@ss.hasPermi('adopt:find:remove')")
    @Log(title = "寻宠", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(petFindService.deletePetFindByIds(ids));
    }
}
