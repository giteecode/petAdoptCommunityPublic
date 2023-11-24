package com.lian.pet.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Desc:

 * @Time: 2022/2/18 13:47
 */
@Data
@ApiModel("添加收藏")
public class AddCollectDTO {
    @ApiModelProperty(value = "用户ID")
    private String userId;
    @ApiModelProperty(value = "来源ID")
    private Integer fromId;
    @ApiModelProperty(value = "类型", notes = "ADOPT FIND CIRCLE")
    private String type;
}
