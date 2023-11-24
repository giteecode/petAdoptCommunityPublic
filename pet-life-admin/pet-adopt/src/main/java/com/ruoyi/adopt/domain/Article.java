package com.ruoyi.adopt.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 文章对象 article
 * 
 * @author ruoyi
 * @date 2022-02-16
 */
public class Article extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 标签 */
    @Excel(name = "标签")
    private String label;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** H5链接 */
    @Excel(name = "H5链接")
    private String url;

    /** 背景色 */
    @Excel(name = "背景色")
    private String bgColor;

    /** 封面图 */
    @Excel(name = "封面图")
    private String picture;

    /** 浏览量 */
    @Excel(name = "浏览量")
    private Long view;

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
    public void setLabel(String label) 
    {
        this.label = label;
    }

    public String getLabel() 
    {
        return label;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setUrl(String url) 
    {
        this.url = url;
    }

    public String getUrl() 
    {
        return url;
    }
    public void setBgColor(String bgColor) 
    {
        this.bgColor = bgColor;
    }

    public String getBgColor() 
    {
        return bgColor;
    }
    public void setPicture(String picture) 
    {
        this.picture = picture;
    }

    public String getPicture() 
    {
        return picture;
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
            .append("label", getLabel())
            .append("title", getTitle())
            .append("url", getUrl())
            .append("bgColor", getBgColor())
            .append("picture", getPicture())
            .append("view", getView())
            .append("isDeleted", getIsDeleted())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
