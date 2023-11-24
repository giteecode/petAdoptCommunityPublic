package com.ruoyi.adopt.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 聊天对象 chat
 * 
 * @author ruoyi
 * @date 2022-02-16
 */
public class Chat extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 发送人id */
    @Excel(name = "发送人id")
    private String fromId;

    /** 接收人id */
    @Excel(name = "接收人id")
    private String toId;

    /** 消息内容 */
    @Excel(name = "消息内容")
    private String content;

    /** 是否已读 */
    @Excel(name = "是否已读")
    private String isDone;

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
    public void setFromId(String fromId) 
    {
        this.fromId = fromId;
    }

    public String getFromId() 
    {
        return fromId;
    }
    public void setToId(String toId) 
    {
        this.toId = toId;
    }

    public String getToId() 
    {
        return toId;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setIsDone(String isDone) 
    {
        this.isDone = isDone;
    }

    public String getIsDone() 
    {
        return isDone;
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
            .append("fromId", getFromId())
            .append("toId", getToId())
            .append("content", getContent())
            .append("isDone", getIsDone())
            .append("isDeleted", getIsDeleted())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
