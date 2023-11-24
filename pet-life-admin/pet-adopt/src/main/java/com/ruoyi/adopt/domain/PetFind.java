package com.ruoyi.adopt.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 寻宠对象 pet_find
 * 
 * @author ruoyi
 * @date 2022-02-16
 */
public class PetFind extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 昵称 */
    @Excel(name = "昵称")
    private String nickname;

    /** 手机号 */
    @Excel(name = "手机号")
    private String phone;

    /** 品种 */
    @Excel(name = "品种")
    private String bread;

    /** 宠物类型 */
    @Excel(name = "宠物类型")
    private String type;

    /** 图片前缀 */
    private String picPrefix;

    /** 图片 */
    private String pictures;

    /** 性别 */
    @Excel(name = "性别")
    private String sex;

    /** 年龄 */
    @Excel(name = "年龄")
    private String age;

    /** 城市 */
    @Excel(name = "城市")
    private String city;

    /** 具体位置 */
    @Excel(name = "具体位置")
    private String address;

    /** 描述 */
    @Excel(name = "描述")
    private String content;

    /** openId */
    private String openId;

    /** 是否完成 */
    @Excel(name = "是否完成")
    private String isFinish;

    /** 浏览量 */
    private Long view;

    /** 是否删除 */
    private String isDeleted;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
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
    public void setBread(String bread) 
    {
        this.bread = bread;
    }

    public String getBread() 
    {
        return bread;
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
    public void setAge(String age) 
    {
        this.age = age;
    }

    public String getAge() 
    {
        return age;
    }
    public void setCity(String city) 
    {
        this.city = city;
    }

    public String getCity() 
    {
        return city;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
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
            .append("bread", getBread())
            .append("type", getType())
            .append("picPrefix", getPicPrefix())
            .append("pictures", getPictures())
            .append("sex", getSex())
            .append("age", getAge())
            .append("city", getCity())
            .append("address", getAddress())
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
