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
@ApiModel("查询寻宠")
public class QueryPetFindDTO extends BasePageQueryReq {
    @ApiModelProperty(value = "openId")
    private String openId;
    @ApiModelProperty(value = "更新时间", notes = "1-近一周 2-近一个月 3-近三个月")
    private String updateTime;
    @ApiModelProperty(value = "城市")
    private String city;
    @ApiModelProperty(value = "类别", notes = "1-猫咪 2-狗狗")
    private String type;
    @ApiModelProperty(value = "性别", notes = "1-男 2-女")
    private String sex;
}
