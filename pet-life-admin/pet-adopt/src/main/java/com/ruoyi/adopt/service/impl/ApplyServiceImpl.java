package com.ruoyi.adopt.service.impl;

import java.util.List;
import java.util.Optional;

import com.ruoyi.adopt.domain.PetAdopt;
import com.ruoyi.adopt.mapper.PetAdoptMapper;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.adopt.mapper.ApplyMapper;
import com.ruoyi.adopt.domain.Apply;
import com.ruoyi.adopt.service.IApplyService;

/**
 * 领养申请Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-03-25
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class ApplyServiceImpl implements IApplyService 
{
    @Autowired
    private ApplyMapper applyMapper;
    @Autowired
    private PetAdoptMapper petAdoptMapper;

    /**
     * 查询领养申请
     * 
     * @param id 领养申请主键
     * @return 领养申请
     */
    @Override
    public Apply selectApplyById(Integer id)
    {
        return applyMapper.selectApplyById(id);
    }

    /**
     * 查询领养申请列表
     * 
     * @param apply 领养申请
     * @return 领养申请
     */
    @Override
    public List<Apply> selectApplyList(Apply apply)
    {
        List<Apply> list = applyMapper.selectApplyList(apply);
        list.forEach(apply1 ->{
            PetAdopt adopt =  petAdoptMapper.selectPetAdoptById(apply1.getAdoptId());
            apply1.setAdoptPetName(Optional.ofNullable(adopt.getTitle()).orElse(""));
        });
        return list;
    }

    /**
     * 新增领养申请
     * 
     * @param apply 领养申请
     * @return 结果
     */
    @Override
    public int insertApply(Apply apply)
    {
        apply.setCreateTime(DateUtils.getNowDate());
        return applyMapper.insertApply(apply);
    }

    /**
     * 修改领养申请
     * 
     * @param apply 领养申请
     * @return 结果
     */
    @Override
    public int updateApply(Apply apply)
    {
        apply.setUpdateTime(DateUtils.getNowDate());
        return applyMapper.updateApply(apply);
    }

    /**
     * 批量删除领养申请
     * 
     * @param ids 需要删除的领养申请主键
     * @return 结果
     */
    @Override
    public int deleteApplyByIds(Integer[] ids)
    {
        return applyMapper.deleteApplyByIds(ids);
    }

    /**
     * 删除领养申请信息
     * 
     * @param id 领养申请主键
     * @return 结果
     */
    @Override
    public int deleteApplyById(Integer id)
    {
        return applyMapper.deleteApplyById(id);
    }
}
