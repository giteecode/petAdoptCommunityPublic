package com.ruoyi.adopt.service.impl;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.adopt.mapper.PetCyclopediaMapper;
import com.ruoyi.adopt.domain.PetCyclopedia;
import com.ruoyi.adopt.service.IPetCyclopediaService;

/**
 * 宠物科普Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-02-16
 */
@Service
public class PetCyclopediaServiceImpl implements IPetCyclopediaService 
{
    @Autowired
    private PetCyclopediaMapper petCyclopediaMapper;

    /**
     * 查询宠物科普
     * 
     * @param id 宠物科普主键
     * @return 宠物科普
     */
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public PetCyclopedia selectPetCyclopediaById(Long id)
    {
        return petCyclopediaMapper.selectPetCyclopediaById(id);
    }

    /**
     * 查询宠物科普列表
     * 
     * @param petCyclopedia 宠物科普
     * @return 宠物科普
     */
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public List<PetCyclopedia> selectPetCyclopediaList(PetCyclopedia petCyclopedia)
    {
        return petCyclopediaMapper.selectPetCyclopediaList(petCyclopedia);
    }

    /**
     * 新增宠物科普
     * 
     * @param petCyclopedia 宠物科普
     * @return 结果
     */
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public int insertPetCyclopedia(PetCyclopedia petCyclopedia)
    {
        petCyclopedia.setCreateTime(DateUtils.getNowDate());
        return petCyclopediaMapper.insertPetCyclopedia(petCyclopedia);
    }

    /**
     * 修改宠物科普
     * 
     * @param petCyclopedia 宠物科普
     * @return 结果
     */
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public int updatePetCyclopedia(PetCyclopedia petCyclopedia)
    {
        petCyclopedia.setUpdateTime(DateUtils.getNowDate());
        return petCyclopediaMapper.updatePetCyclopedia(petCyclopedia);
    }

    /**
     * 批量删除宠物科普
     * 
     * @param ids 需要删除的宠物科普主键
     * @return 结果
     */
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public int deletePetCyclopediaByIds(Long[] ids)
    {
        return petCyclopediaMapper.deletePetCyclopediaByIds(ids);
    }

    /**
     * 删除宠物科普信息
     * 
     * @param id 宠物科普主键
     * @return 结果
     */
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public int deletePetCyclopediaById(Long id)
    {
        return petCyclopediaMapper.deletePetCyclopediaById(id);
    }
}
