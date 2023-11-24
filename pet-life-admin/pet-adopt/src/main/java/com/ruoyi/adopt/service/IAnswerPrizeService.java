package com.ruoyi.adopt.service;

import java.util.List;
import com.ruoyi.adopt.domain.AnswerPrize;

/**
 * 答题Service接口
 * 
 * @author ruoyi
 * @date 2022-02-22
 */
public interface IAnswerPrizeService 
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
     * 批量删除答题
     * 
     * @param ids 需要删除的答题主键集合
     * @return 结果
     */
    public int deleteAnswerPrizeByIds(Long[] ids);

    /**
     * 删除答题信息
     * 
     * @param id 答题主键
     * @return 结果
     */
    public int deleteAnswerPrizeById(Long id);
}
