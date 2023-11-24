package com.ruoyi.adopt.service.impl;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.adopt.mapper.AnswerPrizeMapper;
import com.ruoyi.adopt.domain.AnswerPrize;
import com.ruoyi.adopt.service.IAnswerPrizeService;

/**
 * 答题Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-02-22
 */
@Service
public class AnswerPrizeServiceImpl implements IAnswerPrizeService 
{
    @Autowired
    private AnswerPrizeMapper answerPrizeMapper;

    /**
     * 查询答题
     * 
     * @param id 答题主键
     * @return 答题
     */
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public AnswerPrize selectAnswerPrizeById(Long id)
    {
        return answerPrizeMapper.selectAnswerPrizeById(id);
    }

    /**
     * 查询答题列表
     * 
     * @param answerPrize 答题
     * @return 答题
     */
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public List<AnswerPrize> selectAnswerPrizeList(AnswerPrize answerPrize)
    {
        return answerPrizeMapper.selectAnswerPrizeList(answerPrize);
    }

    /**
     * 新增答题
     * 
     * @param answerPrize 答题
     * @return 结果
     */
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public int insertAnswerPrize(AnswerPrize answerPrize)
    {
        answerPrize.setCreateTime(DateUtils.getNowDate());
        return answerPrizeMapper.insertAnswerPrize(answerPrize);
    }

    /**
     * 修改答题
     * 
     * @param answerPrize 答题
     * @return 结果
     */
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public int updateAnswerPrize(AnswerPrize answerPrize)
    {
        answerPrize.setUpdateTime(DateUtils.getNowDate());
        return answerPrizeMapper.updateAnswerPrize(answerPrize);
    }

    /**
     * 批量删除答题
     * 
     * @param ids 需要删除的答题主键
     * @return 结果
     */
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public int deleteAnswerPrizeByIds(Long[] ids)
    {
        return answerPrizeMapper.deleteAnswerPrizeByIds(ids);
    }

    /**
     * 删除答题信息
     * 
     * @param id 答题主键
     * @return 结果
     */
    @DataSource(value = DataSourceType.SLAVE)
    @Override
    public int deleteAnswerPrizeById(Long id)
    {
        return answerPrizeMapper.deleteAnswerPrizeById(id);
    }
}
