package com.ruoyi.adopt.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 评论对象 comment
 * 
 * @author ruoyi
 * @date 2022-02-16
 */
public class Comment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 父级ID */
    @Excel(name = "父级ID")
    private Long parentId;

    /** 祖级ID */
    @Excel(name = "祖级ID")
    private Long grandId;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** 类别 */
    @Excel(name = "类别")
    private String type;

    /** 所属文章ID */
    @Excel(name = "所属文章ID")
    private Long fromId;

    /** 所属者ID */
    @Excel(name = "所属者ID")
    private String ownerId;

    /** 点赞 */
    @Excel(name = "点赞")
    private Long love;

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
    public void setParentId(Long parentId) 
    {
        this.parentId = parentId;
    }

    public Long getParentId() 
    {
        return parentId;
    }
    public void setGrandId(Long grandId) 
    {
        this.grandId = grandId;
    }

    public Long getGrandId() 
    {
        return grandId;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setFromId(Long fromId) 
    {
        this.fromId = fromId;
    }

    public Long getFromId() 
    {
        return fromId;
    }
    public void setOwnerId(String ownerId) 
    {
        this.ownerId = ownerId;
    }

    public String getOwnerId() 
    {
        return ownerId;
    }
    public void setLove(Long love) 
    {
        this.love = love;
    }

    public Long getLove() 
    {
        return love;
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
            .append("parentId", getParentId())
            .append("grandId", getGrandId())
            .append("content", getContent())
            .append("type", getType())
            .append("fromId", getFromId())
            .append("ownerId", getOwnerId())
            .append("love", getLove())
            .append("isDeleted", getIsDeleted())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
