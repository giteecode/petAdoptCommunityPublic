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
import com.ruoyi.adopt.domain.PetCyclopedia;
import com.ruoyi.adopt.service.IPetCyclopediaService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 宠物科普Controller
 * 
 * @author ruoyi
 * @date 2022-02-16
 */
@RestController
@RequestMapping("/adopt/cyclopedia")
public class PetCyclopediaController extends BaseController
{
    @Autowired
    private IPetCyclopediaService petCyclopediaService;

    /**
     * 查询宠物科普列表
     */
    @PreAuthorize("@ss.hasPermi('adopt:cyclopedia:list')")
    @GetMapping("/list")
    public TableDataInfo list(PetCyclopedia petCyclopedia)
    {
        startPage();
        List<PetCyclopedia> list = petCyclopediaService.selectPetCyclopediaList(petCyclopedia);
        return getDataTable(list);
    }

    /**
     * 导出宠物科普列表
     */
    @PreAuthorize("@ss.hasPermi('adopt:cyclopedia:export')")
    @Log(title = "宠物科普", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PetCyclopedia petCyclopedia)
    {
        List<PetCyclopedia> list = petCyclopediaService.selectPetCyclopediaList(petCyclopedia);
        ExcelUtil<PetCyclopedia> util = new ExcelUtil<PetCyclopedia>(PetCyclopedia.class);
        util.exportExcel(response, list, "宠物科普数据");
    }

    /**
     * 获取宠物科普详细信息
     */
    @PreAuthorize("@ss.hasPermi('adopt:cyclopedia:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(petCyclopediaService.selectPetCyclopediaById(id));
    }

    /**
     * 新增宠物科普
     */
    @PreAuthorize("@ss.hasPermi('adopt:cyclopedia:add')")
    @Log(title = "宠物科普", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PetCyclopedia petCyclopedia)
    {
        return toAjax(petCyclopediaService.insertPetCyclopedia(petCyclopedia));
    }

    /**
     * 修改宠物科普
     */
    @PreAuthorize("@ss.hasPermi('adopt:cyclopedia:edit')")
    @Log(title = "宠物科普", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PetCyclopedia petCyclopedia)
    {
        return toAjax(petCyclopediaService.updatePetCyclopedia(petCyclopedia));
    }

    /**
     * 删除宠物科普
     */
    @PreAuthorize("@ss.hasPermi('adopt:cyclopedia:remove')")
    @Log(title = "宠物科普", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(petCyclopediaService.deletePetCyclopediaByIds(ids));
    }
}
