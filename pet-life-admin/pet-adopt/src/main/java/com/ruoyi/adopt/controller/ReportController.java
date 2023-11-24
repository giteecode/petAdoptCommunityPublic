package com.ruoyi.adopt.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.StringUtils;
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
import com.ruoyi.adopt.domain.Report;
import com.ruoyi.adopt.service.IReportService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 举报Controller
 * 
 * @author ruoyi
 * @date 2023-03-25
 */
@RestController
@RequestMapping("/adopt/report")
public class ReportController extends BaseController
{
    @Autowired
    private IReportService reportService;

    /**
     * 查询举报列表
     */
    @PreAuthorize("@ss.hasPermi('adopt:report:list')")
    @GetMapping("/list")
    public TableDataInfo list(Report report)
    {
        startPage();
        List<Report> list = reportService.selectReportList(report);
        // 组装图片数据
        list.forEach(r ->{
            if(StringUtils.isNotEmpty(r.getPictures())){
                List<String> picList = new ArrayList<>();
               String[] pictures = StringUtils.split(r.getPictures(),",");
               for(String s:pictures){
                  String url =  r.getPicPrefix() + s.replace("\"","");
                  picList.add(url);
               }
               r.setPictureList(picList);
            }
        });
        return getDataTable(list);
    }

    /**
     * 导出举报列表
     */
    @PreAuthorize("@ss.hasPermi('adopt:report:export')")
    @Log(title = "举报", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Report report)
    {
        List<Report> list = reportService.selectReportList(report);
        ExcelUtil<Report> util = new ExcelUtil<Report>(Report.class);
        util.exportExcel(response, list, "举报数据");
    }

    /**
     * 获取举报详细信息
     */
    @PreAuthorize("@ss.hasPermi('adopt:report:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        Report report = reportService.selectReportById(id);
        // 组装图片数据
        if(report != null && StringUtils.isNotEmpty(report.getPictures())){
            List<String> picList = new ArrayList<>();
            String[] pictures = StringUtils.split(report.getPictures(),",");
            for(String s:pictures){
                String url =  report.getPicPrefix() + s.replace("\"","");
                picList.add(url);
            }
            report.setPictureList(picList);
        }
        return AjaxResult.success(report);
    }

    /**
     * 新增举报
     */
    @PreAuthorize("@ss.hasPermi('adopt:report:add')")
    @Log(title = "举报", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Report report)
    {
        return toAjax(reportService.insertReport(report));
    }

    /**
     * 修改举报
     */
    @PreAuthorize("@ss.hasPermi('adopt:report:edit')")
    @Log(title = "举报", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Report report)
    {
        return toAjax(reportService.updateReport(report));
    }

    /**
     * 删除举报
     */
    @PreAuthorize("@ss.hasPermi('adopt:report:remove')")
    @Log(title = "举报", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(reportService.deleteReportByIds(ids));
    }
}
