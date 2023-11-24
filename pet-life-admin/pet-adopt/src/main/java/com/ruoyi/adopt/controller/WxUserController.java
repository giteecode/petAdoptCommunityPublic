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
import com.ruoyi.adopt.domain.WxUser;
import com.ruoyi.adopt.service.IWxUserService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 小程序用户Controller
 * 
 * @author ruoyi
 * @date 2022-02-22
 */
@RestController
@RequestMapping("/adopt/user")
public class WxUserController extends BaseController
{
    @Autowired
    private IWxUserService wxUserService;

    /**
     * 查询小程序用户列表
     */
    @PreAuthorize("@ss.hasPermi('adopt:user:list')")
    @GetMapping("/list")
    public TableDataInfo list(WxUser wxUser)
    {
        startPage();
        List<WxUser> list = wxUserService.selectWxUserList(wxUser);
        return getDataTable(list);
    }

    /**
     * 导出小程序用户列表
     */
    @PreAuthorize("@ss.hasPermi('adopt:user:export')")
    @Log(title = "小程序用户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WxUser wxUser)
    {
        List<WxUser> list = wxUserService.selectWxUserList(wxUser);
        ExcelUtil<WxUser> util = new ExcelUtil<WxUser>(WxUser.class);
        util.exportExcel(response, list, "小程序用户数据");
    }

    /**
     * 获取小程序用户详细信息
     */
    @PreAuthorize("@ss.hasPermi('adopt:user:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(wxUserService.selectWxUserById(id));
    }

    /**
     * 新增小程序用户
     */
    @PreAuthorize("@ss.hasPermi('adopt:user:add')")
    @Log(title = "小程序用户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxUser wxUser)
    {
        return toAjax(wxUserService.insertWxUser(wxUser));
    }

    /**
     * 修改小程序用户
     */
    @PreAuthorize("@ss.hasPermi('adopt:user:edit')")
    @Log(title = "小程序用户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxUser wxUser)
    {
        return toAjax(wxUserService.updateWxUser(wxUser));
    }

    /**
     * 删除小程序用户
     */
    @PreAuthorize("@ss.hasPermi('adopt:user:remove')")
    @Log(title = "小程序用户", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(wxUserService.deleteWxUserByIds(ids));
    }
}
