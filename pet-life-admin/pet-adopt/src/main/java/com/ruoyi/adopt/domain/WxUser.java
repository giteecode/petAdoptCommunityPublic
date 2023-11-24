package com.ruoyi.adopt.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 小程序用户对象 wx_user
 * 
 * @author ruoyi
 * @date 2022-02-22
 */
public class WxUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 用户ID */
    @Excel(name = "用户ID")
    private String openId;

    /** session_key */
    private String sessionKey;

    /** union_id */
    private String unionId;

    /** 用户昵称 */
    @Excel(name = "用户昵称")
    private String nickName;

    /** 头像 */
    @Excel(name = "头像")
    private String avatarUrl;

    /** 城市 */
    @Excel(name = "城市")
    private String city;

    /** 国家 */
    private String country;

    /** 省份 */
    private String province;

    /** 性别 */
    @Excel(name = "性别")
    private String gender;

    /** 手机号 */
    @Excel(name = "手机号")
    private String phone;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 用户类型 (wx、qq、app) */
    @Excel(name = "用户类型 (wx、qq、app)")
    private String userType;

    /** 状态 (0-正常 1-禁用 2-黑名单) */
    @Excel(name = "状态 (0-正常 1-禁用 2-黑名单)")
    private String status;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private String isDeleted;

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

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setOpenId(String openId) 
    {
        this.openId = openId;
    }

    public String getOpenId() 
    {
        return openId;
    }
    public void setSessionKey(String sessionKey) 
    {
        this.sessionKey = sessionKey;
    }

    public String getSessionKey() 
    {
        return sessionKey;
    }
    public void setUnionId(String unionId) 
    {
        this.unionId = unionId;
    }

    public String getUnionId() 
    {
        return unionId;
    }
    public void setNickName(String nickName) 
    {
        this.nickName = nickName;
    }

    public String getNickName() 
    {
        return nickName;
    }
    public void setAvatarUrl(String avatarUrl) 
    {
        this.avatarUrl = avatarUrl;
    }

    public String getAvatarUrl() 
    {
        return avatarUrl;
    }
    public void setCity(String city) 
    {
        this.city = city;
    }

    public String getCity() 
    {
        return city;
    }
    public void setCountry(String country) 
    {
        this.country = country;
    }

    public String getCountry() 
    {
        return country;
    }
    public void setProvince(String province) 
    {
        this.province = province;
    }

    public String getProvince() 
    {
        return province;
    }
    public void setGender(String gender) 
    {
        this.gender = gender;
    }

    public String getGender() 
    {
        return gender;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setUserType(String userType) 
    {
        this.userType = userType;
    }

    public String getUserType() 
    {
        return userType;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setIsDeleted(String isDeleted) 
    {
        this.isDeleted = isDeleted;
    }

    public String getIsDeleted() 
    {
        return isDeleted;
    }

    public String getIdcardFrontPic() {
        return idcardFrontPic;
    }

    public void setIdcardFrontPic(String idcardFrontPic) {
        this.idcardFrontPic = idcardFrontPic;
    }

    public String getIdcardBackPic() {
        return idcardBackPic;
    }

    public void setIdcardBackPic(String idcardBackPic) {
        this.idcardBackPic = idcardBackPic;
    }

    public String getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(String authenticated) {
        this.authenticated = authenticated;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("openId", getOpenId())
            .append("sessionKey", getSessionKey())
            .append("unionId", getUnionId())
            .append("nickName", getNickName())
            .append("avatarUrl", getAvatarUrl())
            .append("city", getCity())
            .append("country", getCountry())
            .append("province", getProvince())
            .append("gender", getGender())
            .append("phone", getPhone())
            .append("email", getEmail())
            .append("userType", getUserType())
            .append("status", getStatus())
            .append("isDeleted", getIsDeleted())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
