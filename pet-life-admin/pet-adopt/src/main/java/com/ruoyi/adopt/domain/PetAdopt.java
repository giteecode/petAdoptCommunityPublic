package com.ruoyi.adopt.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 宠物领养对象 pet_adopt
 * 
 * @author ruoyi
 * @date 2022-02-16
 */
public class PetAdopt extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Integer id;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 昵称 */
    @Excel(name = "昵称")
    private String nickname;

    /** 手机号 */
    @Excel(name = "手机号")
    private String phone;

    /** 宠物类型 */
    @Excel(name = "宠物类型")
    private String type;

    /** 图片前缀 */
    @Excel(name = "图片前缀")
    private String picPrefix;

    /** 图片 */
    @Excel(name = "图片")
    private String pictures;

    /** 性别 */
    @Excel(name = "性别")
    private String sex;

    /** 健康状况 */
    @Excel(name = "健康状况")
    private String physicalCondition;

    /** 年龄 */
    @Excel(name = "年龄")
    private String age;

    /** 领养条件 */
    @Excel(name = "领养条件")
    private String conditions;

    /** 城市 */
    @Excel(name = "城市")
    private String city;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** openId */
    @Excel(name = "openId")
    private String openId;

    /** 送养成功 */
    @Excel(name = "送养成功")
    private String isFinish;

    /** 浏览量 */
    @Excel(name = "浏览量")
    private Long view;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private String isDeleted;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setNickname(String nickname) 
    {
        this.nickname = nickname;
    }

    public String getNickname() 
    {
        return nickname;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setPicPrefix(String picPrefix) 
    {
        this.picPrefix = picPrefix;
    }

    public String getPicPrefix() 
    {
        return picPrefix;
    }
    public void setPictures(String pictures) 
    {
        this.pictures = pictures;
    }

    public String getPictures() 
    {
        return pictures;
    }
    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
    }
    public void setPhysicalCondition(String physicalCondition) 
    {
        this.physicalCondition = physicalCondition;
    }

    public String getPhysicalCondition() 
    {
        return physicalCondition;
    }
    public void setAge(String age) 
    {
        this.age = age;
    }

    public String getAge() 
    {
        return age;
    }
    public void setConditions(String conditions) 
    {
        this.conditions = conditions;
    }

    public String getConditions() 
    {
        return conditions;
    }
    public void setCity(String city) 
    {
        this.city = city;
    }

    public String getCity() 
    {
        return city;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setOpenId(String openId) 
    {
        this.openId = openId;
    }

    public String getOpenId() 
    {
        return openId;
    }
    public void setIsFinish(String isFinish) 
    {
        this.isFinish = isFinish;
    }

    public String getIsFinish() 
    {
        return isFinish;
    }
    public void setView(Long view) 
    {
        this.view = view;
    }

    public Long getView() 
    {
        return view;
    }
    public void setIsDeleted(String isDeleted) 
    {
        this.isDeleted = isDeleted;
    }

    public String getIsDeleted() 
    {
        return isDeleted;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("nickname", getNickname())
            .append("phone", getPhone())
            .append("type", getType())
            .append("picPrefix", getPicPrefix())
            .append("pictures", getPictures())
            .append("sex", getSex())
            .append("physicalCondition", getPhysicalCondition())
            .append("age", getAge())
            .append("conditions", getConditions())
            .append("city", getCity())
            .append("content", getContent())
            .append("openId", getOpenId())
            .append("isFinish", getIsFinish())
            .append("view", getView())
            .append("isDeleted", getIsDeleted())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
