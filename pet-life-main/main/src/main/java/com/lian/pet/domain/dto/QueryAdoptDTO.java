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
@ApiModel("查询领养")
public class QueryAdoptDTO extends BasePageQueryReq {
    @ApiModelProperty(value = "openId")
    private String openId;
    @ApiModelProperty(value = "更新时间")
    private String updateTime;
    @ApiModelProperty(value = "城市")
    private String city;
    @ApiModelProperty(value = "标题模糊查询")
    private String title;
}
