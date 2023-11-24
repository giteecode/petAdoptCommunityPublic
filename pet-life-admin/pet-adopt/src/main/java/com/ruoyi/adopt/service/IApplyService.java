package com.ruoyi.adopt.service;

import java.util.List;
import com.ruoyi.adopt.domain.Apply;

/**
 * 领养申请Service接口
 * 
 * @author ruoyi
 * @date 2023-03-25
 */
public interface IApplyService 
{
    /**
     * 查询领养申请
     * 
     * @param id 领养申请主键
     * @return 领养申请
     */
    public Apply selectApplyById(Integer id);

    /**
     * 查询领养申请列表
     * 
     * @param apply 领养申请
     * @return 领养申请集合
     */
    public List<Apply> selectApplyList(Apply apply);

    /**
     * 新增领养申请
     * 
     * @param apply 领养申请
     * @return 结果
     */
    public int insertApply(Apply apply);

    /**
     * 修改领养申请
     * 
     * @param apply 领养申请
     * @return 结果
     */
    public int updateApply(Apply apply);

    /**
     * 批量删除领养申请
     * 
     * @param ids 需要删除的领养申请主键集合
     * @return 结果
     */
    public int deleteApplyByIds(Integer[] ids);

    /**
     * 删除领养申请信息
     * 
     * @param id 领养申请主键
     * @return 结果
     */
    public int deleteApplyById(Integer id);
}
