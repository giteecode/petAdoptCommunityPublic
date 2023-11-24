package com.lian.pet.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Desc:

 * @Time: 2022/2/12 15:43
 */
@Data
@ApiModel("添加关注")
public class AddUserFollowDTO {
    @ApiModelProperty(value = "用户ID")
    private String userId;
    @ApiModelProperty(value = "关注的用户ID")
    private String followId;
}
