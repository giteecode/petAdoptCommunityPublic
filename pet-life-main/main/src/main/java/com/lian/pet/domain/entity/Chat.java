package com.lian.pet.domain.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 聊天表
 * @author
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("chat")
public class Chat implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 发送人id
     */
    private String fromId;

    /**
     * 接收人id
     */
    private String toId;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 0-未读 1-已读
     */
    private String isDone;

    /**
     * 0-正常 1-删除
     */
    @TableLogic(value = "0", delval = "1")
    private String isDeleted;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}