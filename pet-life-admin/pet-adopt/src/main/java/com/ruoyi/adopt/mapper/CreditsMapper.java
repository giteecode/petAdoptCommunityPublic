package com.ruoyi.adopt.mapper;

import java.util.List;
import com.ruoyi.adopt.domain.Credits;

/**
 * 积分Mapper接口
 * 
 * @author ruoyi
 * @date 2022-02-16
 */
public interface CreditsMapper 
{
    /**
     * 查询积分
     * 
     * @param id 积分主键
     * @return 积分
     */
    public Credits selectCreditsById(Long id);

    /**
     * 查询积分列表
     * 
     * @param credits 积分
     * @return 积分集合
     */
    public List<Credits> selectCreditsList(Credits credits);

    /**
     * 新增积分
     * 
     * @param credits 积分
     * @return 结果
     */
    public int insertCredits(Credits credits);

    /**
     * 修改积分
     * 
     * @param credits 积分
     * @return 结果
     */
    public int updateCredits(Credits credits);

    /**
     * 删除积分
     * 
     * @param id 积分主键
     * @return 结果
     */
    public int deleteCreditsById(Long id);

    /**
     * 批量删除积分
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCreditsByIds(Long[] ids);
}
