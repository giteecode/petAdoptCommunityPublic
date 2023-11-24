package com.lian.pet.domain.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 收藏表
 * @author 
 */
@Accessors(chain = true)
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("collect")
public class Collect implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 类型 （ADOPT FIND CIRCLE）
     */
    private String type;

    /**
     * 用户ID
     */
    private String userid;

    /**
     * 来源ID
     */
    private Integer fromId;

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