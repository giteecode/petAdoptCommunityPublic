package com.ruoyi.adopt.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.List;

/**
 * 举报对象 report
 * 
 * @author ruoyi
 * @date 2023-03-25
 */
public class Report extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 手机号 */
    @Excel(name = "手机号")
    private String phone;

    /** 微信号 */
    @Excel(name = "微信号")
    private String wechat;

    /** 图片前缀 */
    @Excel(name = "图片前缀")
    private String picPrefix;

    /** 图片 */
    @Excel(name = "图片")
    private String pictures;

    /** 举报内容 */
    @Excel(name = "举报内容")
    private String content;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String openId;

    /** 0-正常 1-删除 */
    @Excel(name = "0-正常 1-删除")
    private String isDeleted;

    @Excel(name = "图片集合")
    private List<String> pictureList;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
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
    public void setIsDeleted(String isDeleted) 
    {
        this.isDeleted = isDeleted;
    }

    public String getIsDeleted() 
    {
        return isDeleted;
    }

    public List<String> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<String> pictureList) {
        this.pictureList = pictureList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("phone", getPhone())
            .append("wechat", getWechat())
            .append("picPrefix", getPicPrefix())
            .append("pictures", getPictures())
            .append("content", getContent())
            .append("openId", getOpenId())
            .append("isDeleted", getIsDeleted())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
