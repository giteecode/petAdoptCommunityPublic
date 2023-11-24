package com.ruoyi.adopt.mapper;

import java.util.List;
import com.ruoyi.adopt.domain.PetFind;

/**
 * 寻宠Mapper接口
 * 
 * @author ruoyi
 * @date 2022-02-16
 */
public interface PetFindMapper 
{
    /**
     * 查询寻宠
     * 
     * @param id 寻宠主键
     * @return 寻宠
     */
    public PetFind selectPetFindById(Long id);

    /**
     * 查询寻宠列表
     * 
     * @param petFind 寻宠
     * @return 寻宠集合
     */
    public List<PetFind> selectPetFindList(PetFind petFind);

    /**
     * 新增寻宠
     * 
     * @param petFind 寻宠
     * @return 结果
     */
    public int insertPetFind(PetFind petFind);

    /**
     * 修改寻宠
     * 
     * @param petFind 寻宠
     * @return 结果
     */
    public int updatePetFind(PetFind petFind);

    /**
     * 删除寻宠
     * 
     * @param id 寻宠主键
     * @return 结果
     */
    public int deletePetFindById(Long id);

    /**
     * 批量删除寻宠
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePetFindByIds(Long[] ids);
}
