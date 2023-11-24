package com.lian.pet.domain.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 宠物科普
 * @author 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("pet_cyclopedia")
public class PetCyclopedia implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 图片
     */
    private String picture;

    /**
     * 内容(富文本)
     */
    private String content;

    /**
     * 浏览量
     */
    private Integer view;

    /**
     * 类型(1-标签 2-列表)
     */
    private String type;

    /**
     * 标签背景颜色
     */
    private String backgroundColor;

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