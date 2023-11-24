package com.lian.pet.domain.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 答题赢奖
 * @author 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("answer_prize")
public class AnswerPrize implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 题目
     */
    private String subject;

    /**
     * 选项 [数组]
     */
    private String options;

    /**
     * 正确答案
     */
    private String answer;

    /**
     * 类型
     */
    private String type;

    /**
     * 0-正常 1-删除
     */
    @TableLogic(value = "0", delval = "1")
    private String isDeleted;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}