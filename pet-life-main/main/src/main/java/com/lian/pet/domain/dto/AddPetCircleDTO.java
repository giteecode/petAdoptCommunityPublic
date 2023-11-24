package com.lian.pet.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Desc:

 * @Time: 2022/1/25 18:23
 */
@Data
@ApiModel("新增动态")
public class AddPetCircleDTO {
    @ApiModelProperty(value = "用户ID")
    private String openId;
    @ApiModelProperty(value = "内容")
    private String content;
    @ApiModelProperty(value = "图片")
    private List<String> pictures;
}
