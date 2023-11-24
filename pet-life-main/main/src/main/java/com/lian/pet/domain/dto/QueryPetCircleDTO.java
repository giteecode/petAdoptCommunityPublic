package com.lian.pet.domain.dto;

import com.lian.pet.domain.page.BasePageQueryReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Desc:

 * @Time: 2022/1/25 18:23
 */
@Data
@ApiModel("查询宠物圈")
public class QueryPetCircleDTO extends BasePageQueryReq {
    /**
     * 查询用户发布的列表时用到
     */
    private String openId;
    @ApiModelProperty(value = "标题模糊查询")
    private String title;
}
