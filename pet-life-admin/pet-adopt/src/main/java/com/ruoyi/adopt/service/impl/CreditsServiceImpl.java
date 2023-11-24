package com.ruoyi.adopt.service.impl;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.adopt.mapper.CreditsMapper;
import com.ruoyi.adopt.domain.Credits;
import com.ruoyi.adopt.service.ICreditsService;

/**
 * 积分Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-02-16
 */
@Service
public class CreditsServiceImpl implements ICreditsService 
{
    @Autowired
    private CreditsMapper creditsMapper;

    /**
     * 查询积分
     * 
     * @param id 积分主键
     * @return 积分
     */
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public Credits selectCreditsById(Long id)
    {
        return creditsMapper.selectCreditsById(id);
    }

    /**
     * 查询积分列表
     * 
     * @param credits 积分
     * @return 积分
     */
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public List<Credits> selectCreditsList(Credits credits)
    {
        return creditsMapper.selectCreditsList(credits);
    }

    /**
     * 新增积分
     * 
     * @param credits 积分
     * @return 结果
     */
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public int insertCredits(Credits credits)
    {
        credits.setCreateTime(DateUtils.getNowDate());
        return creditsMapper.insertCredits(credits);
    }

    /**
     * 修改积分
     * 
     * @param credits 积分
     * @return 结果
     */
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public int updateCredits(Credits credits)
    {
        credits.setUpdateTime(DateUtils.getNowDate());
        return creditsMapper.updateCredits(credits);
    }

    /**
     * 批量删除积分
     * 
     * @param ids 需要删除的积分主键
     * @return 结果
     */
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public int deleteCreditsByIds(Long[] ids)
    {
        return creditsMapper.deleteCreditsByIds(ids);
    }

    /**
     * 删除积分信息
     * 
     * @param id 积分主键
     * @return 结果
     */
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public int deleteCreditsById(Long id)
    {
        return creditsMapper.deleteCreditsById(id);
    }
}
