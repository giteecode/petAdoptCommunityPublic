package com.lian.pet.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 申请表
 * @author 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("apply")
public class Apply implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 关联pet_adopt的id
     */
    private Integer adoptId;

    private String sex;
    private String age;
    private String petExperience;
    private String petNow;
    private String house;
    private String marriage;
    private String monthlyIncome;
    private String work;
    private String province;
    private String city;

    private String phone;

    /**
     * 微信号
     */
    private String wechat;
    /**
     * 领养理由
     */
    private String content;

    private String openId;
    private String ownerOpenId;

    /**
     * 状态:0-待审核 1-通过 2-拒绝
     */
    private String status;


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