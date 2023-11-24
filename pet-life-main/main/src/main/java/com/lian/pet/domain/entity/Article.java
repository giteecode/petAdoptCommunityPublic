package com.lian.pet.domain.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文章表
 * @author 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("article")
public class Article implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 标签
     */
    private String label;

    /**
     * 标题
     */
    private String title;

    /**
     * H5链接
     */
    private String url;

    /**
     * 背景色
     */
    private String bgColor;

    /**
     * 封面图
     */
    private String picture;

    /**
     * 浏览量
     */
    private Integer view;

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