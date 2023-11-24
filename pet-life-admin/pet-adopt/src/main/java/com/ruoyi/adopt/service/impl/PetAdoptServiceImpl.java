package com.ruoyi.adopt.service.impl;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.adopt.mapper.PetAdoptMapper;
import com.ruoyi.adopt.domain.PetAdopt;
import com.ruoyi.adopt.service.IPetAdoptService;

/**
 * 宠物领养Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-02-16
 */
@Service
public class PetAdoptServiceImpl implements IPetAdoptService 
{
    @Autowired
    private PetAdoptMapper petAdoptMapper;

    /**
     * 查询宠物领养
     * 
     * @param id 宠物领养主键
     * @return 宠物领养
     */
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public PetAdopt selectPetAdoptById(Integer id)
    {
        return petAdoptMapper.selectPetAdoptById(id);
    }

    /**
     * 查询宠物领养列表
     * 
     * @param petAdopt 宠物领养
     * @return 宠物领养
     */
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public List<PetAdopt> selectPetAdoptList(PetAdopt petAdopt)
    {
        return petAdoptMapper.selectPetAdoptList(petAdopt);
    }

    /**
     * 新增宠物领养
     * 
     * @param petAdopt 宠物领养
     * @return 结果
     */
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public int insertPetAdopt(PetAdopt petAdopt)
    {
        petAdopt.setCreateTime(DateUtils.getNowDate());
        return petAdoptMapper.insertPetAdopt(petAdopt);
    }

    /**
     * 修改宠物领养
     * 
     * @param petAdopt 宠物领养
     * @return 结果
     */
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public int updatePetAdopt(PetAdopt petAdopt)
    {
        petAdopt.setUpdateTime(DateUtils.getNowDate());
        return petAdoptMapper.updatePetAdopt(petAdopt);
    }

    /**
     * 批量删除宠物领养
     * 
     * @param ids 需要删除的宠物领养主键
     * @return 结果
     */
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public int deletePetAdoptByIds(Integer[] ids)
    {
        return petAdoptMapper.deletePetAdoptByIds(ids);
    }

    /**
     * 删除宠物领养信息
     * 
     * @param id 宠物领养主键
     * @return 结果
     */
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public int deletePetAdoptById(Integer id)
    {
        return petAdoptMapper.deletePetAdoptById(id);
    }
}
