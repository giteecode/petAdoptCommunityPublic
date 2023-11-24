package com.lian.pet.domain.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 宠物领养
 * @author 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("pet_adopt")
public class PetAdopt implements Serializable {
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
     * 宠物类型（1 猫咪 2 狗狗）
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
     * 性别
     */
    private String sex;

    /**
     * 健康状况（数组）
     */
    private String physicalCondition;

    /**
     * 年龄
     */
    private String age;

    /**
     * 领养条件（数组）
     */
    private String conditions;

    /**
     * 城市
     */
    private String city;

    /**
     * 内容
     */
    private String content;

    /**
     * 是否送养成功（0-已送养 1-正在送养）
     */
    private String isFinish;

    private String openId;
    //领养人id
    private String adopterOpenId;

    /**
     * 浏览量
     */
    private int view;

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