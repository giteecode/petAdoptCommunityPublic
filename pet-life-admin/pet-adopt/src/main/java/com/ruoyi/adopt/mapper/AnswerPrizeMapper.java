package com.ruoyi.adopt.mapper;

import java.util.List;
import com.ruoyi.adopt.domain.AnswerPrize;

/**
 * 答题Mapper接口
 * 
 * @author ruoyi
 * @date 2022-02-22
 */
public interface AnswerPrizeMapper 
{
    /**
     * 查询答题
     * 
     * @param id 答题主键
     * @return 答题
     */
    public AnswerPrize selectAnswerPrizeById(Long id);

    /**
     * 查询答题列表
     * 
     * @param answerPrize 答题
     * @return 答题集合
     */
    public List<AnswerPrize> selectAnswerPrizeList(AnswerPrize answerPrize);

    /**
     * 新增答题
     * 
     * @param answerPrize 答题
     * @return 结果
     */
    public int insertAnswerPrize(AnswerPrize answerPrize);

    /**
     * 修改答题
     * 
     * @param answerPrize 答题
     * @return 结果
     */
    public int updateAnswerPrize(AnswerPrize answerPrize);

    /**
     * 删除答题
     * 
     * @param id 答题主键
     * @return 结果
     */
    public int deleteAnswerPrizeById(Long id);

    /**
     * 批量删除答题
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAnswerPrizeByIds(Long[] ids);
}
