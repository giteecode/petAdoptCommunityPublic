package com.ruoyi.adopt.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 领养申请对象 apply
 * 
 * @author ruoyi
 * @date 2023-03-25
 */
public class Apply extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** 领养宠物id */
    @Excel(name = "领养宠物id")
    private Integer adoptId;

    private String adoptPetName;

    /** 性别（1 男, 2 女） */
    @Excel(name = "性别", readConverterExp = "1=,男=,,2=,女=")
    private String sex;

    /** 年龄 */
    @Excel(name = "年龄")
    private String age;

    /** 养宠经验（0 无, 1 有） */
    @Excel(name = "养宠经验", readConverterExp = "0=,无=,,1=,有=")
    private String petExperience;

    /** 目前宠物（0 无, 1 有猫,2 有狗,3 猫狗） */
    @Excel(name = "目前宠物", readConverterExp = "0=,无=,,1=,有=猫,2,有=狗,3,猫=狗")
    private String petNow;

    /** 住房情况（自有住房，整租，合租，其他） */
    @Excel(name = "住房情况", readConverterExp = "自=有住房，整租，合租，其他")
    private String house;

    /** 婚姻情况（单身，恋爱，已婚） */
    @Excel(name = "婚姻情况", readConverterExp = "单=身，恋爱，已婚")
    private String marriage;

    /** 月收入 */
    @Excel(name = "月收入")
    private String monthlyIncome;

    /** 职业 */
    @Excel(name = "职业")
    private String work;

    /** 省份 */
    @Excel(name = "省份")
    private String province;

    /** 城市 */
    @Excel(name = "城市")
    private String city;

    /** 手机号 */
    @Excel(name = "手机号")
    private String phone;

    /** 微信号 */
    @Excel(name = "微信号")
    private String wechat;

    /** 领养理由 */
    @Excel(name = "领养理由")
    private String content;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String openId;

    /** 主人id */
    @Excel(name = "主人id")
    private String ownerOpenId;

    /** 状态:0-待审核 1-通过 2-拒绝 */
    @Excel(name = "状态:0-待审核 1-通过 2-拒绝")
    private String status;

    /** 0-正常 1-删除 */
    @Excel(name = "0-正常 1-删除")
    private String isDeleted;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setAdoptId(Integer adoptId) 
    {
        this.adoptId = adoptId;
    }

    public Integer getAdoptId() 
    {
        return adoptId;
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
    public void setPetExperience(String petExperience) 
    {
        this.petExperience = petExperience;
    }

    public String getPetExperience() 
    {
        return petExperience;
    }
    public void setPetNow(String petNow) 
    {
        this.petNow = petNow;
    }

    public String getPetNow() 
    {
        return petNow;
    }
    public void setHouse(String house) 
    {
        this.house = house;
    }

    public String getHouse() 
    {
        return house;
    }
    public void setMarriage(String marriage) 
    {
        this.marriage = marriage;
    }

    public String getMarriage() 
    {
        return marriage;
    }
    public void setMonthlyIncome(String monthlyIncome) 
    {
        this.monthlyIncome = monthlyIncome;
    }

    public String getMonthlyIncome() 
    {
        return monthlyIncome;
    }
    public void setWork(String work) 
    {
        this.work = work;
    }

    public String getWork() 
    {
        return work;
    }
    public void setProvince(String province) 
    {
        this.province = province;
    }

    public String getProvince() 
    {
        return province;
    }
    public void setCity(String city) 
    {
        this.city = city;
    }

    public String getCity() 
    {
        return city;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setWechat(String wechat) 
    {
        this.wechat = wechat;
    }

    public String getWechat() 
    {
        return wechat;
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
    public void setOwnerOpenId(String ownerOpenId) 
    {
        this.ownerOpenId = ownerOpenId;
    }

    public String getOwnerOpenId() 
    {
        return ownerOpenId;
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

    public String getAdoptPetName() {
        return adoptPetName;
    }

    public void setAdoptPetName(String adoptPetName) {
        this.adoptPetName = adoptPetName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("adoptId", getAdoptId())
            .append("sex", getSex())
            .append("age", getAge())
            .append("petExperience", getPetExperience())
            .append("petNow", getPetNow())
            .append("house", getHouse())
            .append("marriage", getMarriage())
            .append("monthlyIncome", getMonthlyIncome())
            .append("work", getWork())
            .append("province", getProvince())
            .append("city", getCity())
            .append("phone", getPhone())
            .append("wechat", getWechat())
            .append("content", getContent())
            .append("openId", getOpenId())
            .append("ownerOpenId", getOwnerOpenId())
            .append("status", getStatus())
            .append("isDeleted", getIsDeleted())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
