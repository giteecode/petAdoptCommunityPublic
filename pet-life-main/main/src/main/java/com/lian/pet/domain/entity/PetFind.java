package com.lian.pet.domain.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 寻宠
 * @author 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("pet_find")
public class PetFind implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 品种
     */
    private String bread;

    /**
     * 宠物类型（1-猫咪 2-狗狗）
     */
    private String type;

    /**
     * 图片前缀
     */
    private String picPrefix;

    /**
     * 图片
     */
    private String pictures;

    /**
     * 性别（0-未知 1-男 2-女）
     */
    private String sex;

    /**
     * 年龄
     */
    private String age;

    /**
     * 城市
     */
    private String city;

    /**
     * 具体位置
     */
    private String address;

    /**
     * 描述
     */
    private String content;

    private String openId;

    /**
     * 是否完成（0-已完成 1-进行中）
     */
    private String isFinish;

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