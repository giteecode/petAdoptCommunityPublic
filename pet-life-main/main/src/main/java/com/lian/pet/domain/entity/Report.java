package com.lian.pet.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 举报表
 * @author 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("report")
public class Report implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 微信号
     */
    private String wechat;
    /**
     * 图片前缀
     */
    private String picPrefix;
    /**
     * 图片
     */
    private String pictures;

    /**
     * 举报内容
     */
    private String content;

    private String openId;


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