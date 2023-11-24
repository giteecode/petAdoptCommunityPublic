package com.ruoyi.adopt.service.impl;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.adopt.mapper.PetFindMapper;
import com.ruoyi.adopt.domain.PetFind;
import com.ruoyi.adopt.service.IPetFindService;

/**
 * 寻宠Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-02-16
 */
@Service
public class PetFindServiceImpl implements IPetFindService 
{
    @Autowired
    private PetFindMapper petFindMapper;

    /**
     * 查询寻宠
     * 
     * @param id 寻宠主键
     * @return 寻宠
     */
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public PetFind selectPetFindById(Long id)
    {
        return petFindMapper.selectPetFindById(id);
    }

    /**
     * 查询寻宠列表
     * 
     * @param petFind 寻宠
     * @return 寻宠
     */
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public List<PetFind> selectPetFindList(PetFind petFind)
    {
        return petFindMapper.selectPetFindList(petFind);
    }

    /**
     * 新增寻宠
     * 
     * @param petFind 寻宠
     * @return 结果
     */
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public int insertPetFind(PetFind petFind)
    {
        petFind.setCreateTime(DateUtils.getNowDate());
        return petFindMapper.insertPetFind(petFind);
    }

    /**
     * 修改寻宠
     * 
     * @param petFind 寻宠
     * @return 结果
     */
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public int updatePetFind(PetFind petFind)
    {
        petFind.setUpdateTime(DateUtils.getNowDate());
        return petFindMapper.updatePetFind(petFind);
    }

    /**
     * 批量删除寻宠
     * 
     * @param ids 需要删除的寻宠主键
     * @return 结果
     */
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public int deletePetFindByIds(Long[] ids)
    {
        return petFindMapper.deletePetFindByIds(ids);
    }

    /**
     * 删除寻宠信息
     * 
     * @param id 寻宠主键
     * @return 结果
     */
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public int deletePetFindById(Long id)
    {
        return petFindMapper.deletePetFindById(id);
    }
}
