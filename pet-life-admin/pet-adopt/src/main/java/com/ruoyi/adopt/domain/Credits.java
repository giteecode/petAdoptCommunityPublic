package com.ruoyi.adopt.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 积分对象 credits
 * 
 * @author ruoyi
 * @date 2022-02-16
 */
public class Credits extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 用户ID */
    @Excel(name = "用户ID")
    private String openId;

    /** 积分 */
    @Excel(name = "积分")
    private Long creditsNum;

    /** 签到天数 */
    @Excel(name = "签到天数")
    private Long dayNum;

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
    public void setOpenId(String openId) 
    {
        this.openId = openId;
    }

    public String getOpenId() 
    {
        return openId;
    }
    public void setCreditsNum(Long creditsNum) 
    {
        this.creditsNum = creditsNum;
    }

    public Long getCreditsNum() 
    {
        return creditsNum;
    }
    public void setDayNum(Long dayNum) 
    {
        this.dayNum = dayNum;
    }

    public Long getDayNum() 
    {
        return dayNum;
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
            .append("openId", getOpenId())
            .append("creditsNum", getCreditsNum())
            .append("dayNum", getDayNum())
            .append("isDeleted", getIsDeleted())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
