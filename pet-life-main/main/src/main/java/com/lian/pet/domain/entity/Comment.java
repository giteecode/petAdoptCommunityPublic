package com.lian.pet.domain.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 评论表
 * @author 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("comment")
public class Comment implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 父级ID 为第一级时为null
     */
    private Integer parentId;

    /**
     * 祖级ID 为第一级/第二级时为null
     */
    private Integer grandId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 类型 ADOPT FIND CIRCLE
     */
    private String type;

    /**
     * 所属文章ID
     */
    private Integer fromId;

    /**
     * 所属者ID
     */
    private String ownerId;

    /**
     * 点赞
     */
    private Integer love;

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