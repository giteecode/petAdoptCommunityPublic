package com.lian.pet.domain.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.*;
import lombok.*;

/**
 * 用户表
 * @author 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("wx_user")
public class WxUser implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String openId;

    private String sessionKey;

    private String unionId;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String avatarUrl;

    /**
     * 城市
     */
    private String city;

    /**
     * 国家
     */
    private String country;

    /**
     * 省份
     */
    private String province;

    /**
     * 性别
     */
    private String gender;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户类型 (wx、qq、app)
     */
    private String userType;

    /**
     * 状态 (0-正常 1-禁用 2-黑名单)
     */
    private String status;
    /**
     * 身份证正面照
     */
    private String idcardFrontPic;
    /**
     * 身份证背面照
     */
    private String idcardBackPic;
   /**
     * 是否实名认证 (0-未实名 1-已实名)
     */
    private String authenticated;

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