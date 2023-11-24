package com.lian.pet.domain.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 用户 答题 关联表
 * @author 
 */
@Accessors(chain = true)
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("collect_user_answer")
public class CollectUserAnswer implements Serializable {
    /**
     * 用户ID
     */
    private String userId;

    /**
     * 答题ID
     */
    private Integer answerId;

    /**
     * 1-答对 2-答错
     */
    private String type;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    private static final long serialVersionUID = 1L;
}