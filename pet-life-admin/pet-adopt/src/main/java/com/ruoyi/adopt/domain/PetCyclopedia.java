package com.ruoyi.adopt.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 宠物科普对象 pet_cyclopedia
 * 
 * @author ruoyi
 * @date 2022-02-16
 */
public class PetCyclopedia extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 图片 */
    @Excel(name = "图片")
    private String picture;

    /** 内容(富文本) */
    @Excel(name = "内容(富文本)")
    private String content;

    /** 浏览量 */
    @Excel(name = "浏览量")
    private Long view;

    /** 类型 */
    @Excel(name = "类型")
    private String type;

    /** 标签背景颜色 */
    @Excel(name = "标签背景颜色")
    private String backgroundColor;

    /** 是否删除 */
    @Excel(name = "是否删除")
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
    public void setPicture(String picture) 
    {
        this.picture = picture;
    }

    public String getPicture() 
    {
        return picture;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setView(Long view) 
    {
        this.view = view;
    }

    public Long getView() 
    {
        return view;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setBackgroundColor(String backgroundColor) 
    {
        this.backgroundColor = backgroundColor;
    }

    public String getBackgroundColor() 
    {
        return backgroundColor;
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
            .append("picture", getPicture())
            .append("content", getContent())
            .append("view", getView())
            .append("type", getType())
            .append("backgroundColor", getBackgroundColor())
            .append("isDeleted", getIsDeleted())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
