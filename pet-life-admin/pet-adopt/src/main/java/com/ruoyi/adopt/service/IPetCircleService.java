package com.ruoyi.adopt.service;

import java.util.List;
import com.ruoyi.adopt.domain.PetCircle;

/**
 * 动态Service接口
 * 
 * @author ruoyi
 * @date 2022-02-16
 */
public interface IPetCircleService 
{
    /**
     * 查询动态
     * 
     * @param id 动态主键
     * @return 动态
     */
    public PetCircle selectPetCircleById(Long id);

    /**
     * 查询动态列表
     * 
     * @param petCircle 动态
     * @return 动态集合
     */
    public List<PetCircle> selectPetCircleList(PetCircle petCircle);

    /**
     * 新增动态
     * 
     * @param petCircle 动态
     * @return 结果
     */
    public int insertPetCircle(PetCircle petCircle);

    /**
     * 修改动态
     * 
     * @param petCircle 动态
     * @return 结果
     */
    public int updatePetCircle(PetCircle petCircle);

    /**
     * 批量删除动态
     * 
     * @param ids 需要删除的动态主键集合
     * @return 结果
     */
    public int deletePetCircleByIds(Long[] ids);

    /**
     * 删除动态信息
     * 
     * @param id 动态主键
     * @return 结果
     */
    public int deletePetCircleById(Long id);
}
