package com.lian.pet.domain.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 积分表
 * @author 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("credits")
public class Credits implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户ID
     */
    private String openId;

    /**
     * 积分
     */
    private Integer creditsNum;

    /**
     * 签到天数
     */
    private Integer dayNum;

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