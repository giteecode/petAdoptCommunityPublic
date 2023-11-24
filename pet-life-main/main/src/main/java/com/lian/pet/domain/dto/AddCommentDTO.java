package com.lian.pet.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Desc:

 * @Time: 2022/2/2 20:07
 */
@Data
@ApiModel("查询评论")
public class AddCommentDTO {
    @ApiModelProperty(value = "父级ID")
    private Integer parentId;
    @ApiModelProperty(value = "祖级ID")
    private Integer grandId;
    @ApiModelProperty(value = "所属者ID")
    private String ownerId;
    @ApiModelProperty(value = "所属文章ID")
    private Integer formId;
    @ApiModelProperty(value = "评论内容")
    private String content;
    @ApiModelProperty(value = "type", notes = "ADOPT FIND CIRCLE")
    private String type;
}
