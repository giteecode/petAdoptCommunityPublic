package com.ruoyi.adopt.service.impl;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.adopt.mapper.PetCircleMapper;
import com.ruoyi.adopt.domain.PetCircle;
import com.ruoyi.adopt.service.IPetCircleService;

/**
 * 动态Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-02-16
 */
@Service
public class PetCircleServiceImpl implements IPetCircleService 
{
    @Autowired
    private PetCircleMapper petCircleMapper;

    /**
     * 查询动态
     * 
     * @param id 动态主键
     * @return 动态
     */
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public PetCircle selectPetCircleById(Long id)
    {
        return petCircleMapper.selectPetCircleById(id);
    }

    /**
     * 查询动态列表
     * 
     * @param petCircle 动态
     * @return 动态
     */
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public List<PetCircle> selectPetCircleList(PetCircle petCircle)
    {
        return petCircleMapper.selectPetCircleList(petCircle);
    }

    /**
     * 新增动态
     * 
     * @param petCircle 动态
     * @return 结果
     */
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public int insertPetCircle(PetCircle petCircle)
    {
        petCircle.setCreateTime(DateUtils.getNowDate());
        return petCircleMapper.insertPetCircle(petCircle);
    }

    /**
     * 修改动态
     * 
     * @param petCircle 动态
     * @return 结果
     */
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public int updatePetCircle(PetCircle petCircle)
    {
        petCircle.setUpdateTime(DateUtils.getNowDate());
        return petCircleMapper.updatePetCircle(petCircle);
    }

    /**
     * 批量删除动态
     * 
     * @param ids 需要删除的动态主键
     * @return 结果
     */
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public int deletePetCircleByIds(Long[] ids)
    {
        return petCircleMapper.deletePetCircleByIds(ids);
    }

    /**
     * 删除动态信息
     * 
     * @param id 动态主键
     * @return 结果
     */
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public int deletePetCircleById(Long id)
    {
        return petCircleMapper.deletePetCircleById(id);
    }
}
