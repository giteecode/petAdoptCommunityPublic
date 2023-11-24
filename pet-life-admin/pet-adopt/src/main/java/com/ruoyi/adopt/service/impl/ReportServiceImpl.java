package com.ruoyi.adopt.service.impl;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.adopt.mapper.ReportMapper;
import com.ruoyi.adopt.domain.Report;
import com.ruoyi.adopt.service.IReportService;

/**
 * 举报Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-03-25
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class ReportServiceImpl implements IReportService 
{
    @Autowired
    private ReportMapper reportMapper;

    /**
     * 查询举报
     * 
     * @param id 举报主键
     * @return 举报
     */
    @Override
    public Report selectReportById(Integer id)
    {
        return reportMapper.selectReportById(id);
    }

    /**
     * 查询举报列表
     * 
     * @param report 举报
     * @return 举报
     */
    @Override
    public List<Report> selectReportList(Report report)
    {
        return reportMapper.selectReportList(report);
    }

    /**
     * 新增举报
     * 
     * @param report 举报
     * @return 结果
     */
    @Override
    public int insertReport(Report report)
    {
        report.setCreateTime(DateUtils.getNowDate());
        return reportMapper.insertReport(report);
    }

    /**
     * 修改举报
     * 
     * @param report 举报
     * @return 结果
     */
    @Override
    public int updateReport(Report report)
    {
        report.setUpdateTime(DateUtils.getNowDate());
        return reportMapper.updateReport(report);
    }

    /**
     * 批量删除举报
     * 
     * @param ids 需要删除的举报主键
     * @return 结果
     */
    @Override
    public int deleteReportByIds(Integer[] ids)
    {
        return reportMapper.deleteReportByIds(ids);
    }

    /**
     * 删除举报信息
     * 
     * @param id 举报主键
     * @return 结果
     */
    @Override
    public int deleteReportById(Integer id)
    {
        return reportMapper.deleteReportById(id);
    }
}
