package com.ruoyi.adopt.mapper;

import java.util.List;
import com.ruoyi.adopt.domain.PetCyclopedia;

/**
 * 宠物科普Mapper接口
 * 
 * @author ruoyi
 * @date 2022-02-16
 */
public interface PetCyclopediaMapper 
{
    /**
     * 查询宠物科普
     * 
     * @param id 宠物科普主键
     * @return 宠物科普
     */
    public PetCyclopedia selectPetCyclopediaById(Long id);

    /**
     * 查询宠物科普列表
     * 
     * @param petCyclopedia 宠物科普
     * @return 宠物科普集合
     */
    public List<PetCyclopedia> selectPetCyclopediaList(PetCyclopedia petCyclopedia);

    /**
     * 新增宠物科普
     * 
     * @param petCyclopedia 宠物科普
     * @return 结果
     */
    public int insertPetCyclopedia(PetCyclopedia petCyclopedia);

    /**
     * 修改宠物科普
     * 
     * @param petCyclopedia 宠物科普
     * @return 结果
     */
    public int updatePetCyclopedia(PetCyclopedia petCyclopedia);

    /**
     * 删除宠物科普
     * 
     * @param id 宠物科普主键
     * @return 结果
     */
    public int deletePetCyclopediaById(Long id);

    /**
     * 批量删除宠物科普
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePetCyclopediaByIds(Long[] ids);
}
