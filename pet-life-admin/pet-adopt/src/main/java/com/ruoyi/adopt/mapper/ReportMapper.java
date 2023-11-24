package com.ruoyi.adopt.mapper;

import java.util.List;
import com.ruoyi.adopt.domain.Report;

/**
 * 举报Mapper接口
 * 
 * @author ruoyi
 * @date 2023-03-25
 */
public interface ReportMapper 
{
    /**
     * 查询举报
     * 
     * @param id 举报主键
     * @return 举报
     */
    public Report selectReportById(Integer id);

    /**
     * 查询举报列表
     * 
     * @param report 举报
     * @return 举报集合
     */
    public List<Report> selectReportList(Report report);

    /**
     * 新增举报
     * 
     * @param report 举报
     * @return 结果
     */
    public int insertReport(Report report);

    /**
     * 修改举报
     * 
     * @param report 举报
     * @return 结果
     */
    public int updateReport(Report report);

    /**
     * 删除举报
     * 
     * @param id 举报主键
     * @return 结果
     */
    public int deleteReportById(Integer id);

    /**
     * 批量删除举报
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteReportByIds(Integer[] ids);
}
