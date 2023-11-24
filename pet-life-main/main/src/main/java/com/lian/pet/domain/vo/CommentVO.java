package com.lian.pet.domain.vo;

import com.lian.pet.common.basic.utils.DateUtil;
import com.lian.pet.domain.entity.Comment;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @Desc:

 * @Time: 2022/2/2 20:03
 */
@Data
@Builder
@ApiModel("新增评论")
public class CommentVO {
    @ApiModelProperty(value = "ID")
    private Integer id;
    @ApiModelProperty(value = "父级ID")
    private Integer parentId;
    @ApiModelProperty(value = "祖级ID")
    private Integer grandId;
    @ApiModelProperty(value = "父级昵称")
    private String parentName;
    @ApiModelProperty(value = "所属者ID")
    private String ownerId;
    @ApiModelProperty(value = "所属文章ID")
    private Integer fromId;
    @ApiModelProperty(value = "评论内容")
    private String content;
    @ApiModelProperty(value = "发布时间")
    private String createTime;
    @ApiModelProperty(value = "点赞")
    private Integer love;
    @ApiModelProperty(value = "用户昵称")
    private String nickname;
    @ApiModelProperty(value = "头像")
    private String avatarUrl;

    public static CommentVO fromComment(Comment entity) {
        return CommentVO.builder()
                .id(entity.getId())
                .parentId(entity.getParentId())
                .grandId(entity.getGrandId())
                .ownerId(entity.getOwnerId())
                .fromId(entity.getFromId())
                .content(entity.getContent())
                .createTime(DateUtil.dateToString(entity.getCreateTime()))
                .love(entity.getLove())
                .build();
    }
}
