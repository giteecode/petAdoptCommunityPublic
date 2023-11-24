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
import com.ruoyi.adopt.domain.PetAdopt;
import com.ruoyi.adopt.service.IPetAdoptService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 宠物领养Controller
 * 
 * @author ruoyi
 * @date 2022-02-16
 */
@RestController
@RequestMapping("/adopt/adopt")
public class PetAdoptController extends BaseController
{
    @Autowired
    private IPetAdoptService petAdoptService;

    /**
     * 查询宠物领养列表
     */
    @PreAuthorize("@ss.hasPermi('adopt:adopt:list')")
    @GetMapping("/list")
    public TableDataInfo list(PetAdopt petAdopt)
    {
        startPage();
        List<PetAdopt> list = petAdoptService.selectPetAdoptList(petAdopt);
        return getDataTable(list);
    }

    /**
     * 导出宠物领养列表
     */
    @PreAuthorize("@ss.hasPermi('adopt:adopt:export')")
    @Log(title = "宠物领养", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PetAdopt petAdopt)
    {
        List<PetAdopt> list = petAdoptService.selectPetAdoptList(petAdopt);
        ExcelUtil<PetAdopt> util = new ExcelUtil<PetAdopt>(PetAdopt.class);
        util.exportExcel(response, list, "宠物领养数据");
    }

    /**
     * 获取宠物领养详细信息
     */
    @PreAuthorize("@ss.hasPermi('adopt:adopt:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(petAdoptService.selectPetAdoptById(id));
    }

    /**
     * 新增宠物领养
     */
    @PreAuthorize("@ss.hasPermi('adopt:adopt:add')")
    @Log(title = "宠物领养", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PetAdopt petAdopt)
    {
        return toAjax(petAdoptService.insertPetAdopt(petAdopt));
    }

    /**
     * 修改宠物领养
     */
    @PreAuthorize("@ss.hasPermi('adopt:adopt:edit')")
    @Log(title = "宠物领养", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PetAdopt petAdopt)
    {
        return toAjax(petAdoptService.updatePetAdopt(petAdopt));
    }

    /**
     * 删除宠物领养
     */
    @PreAuthorize("@ss.hasPermi('adopt:adopt:remove')")
    @Log(title = "宠物领养", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(petAdoptService.deletePetAdoptByIds(ids));
    }
}
