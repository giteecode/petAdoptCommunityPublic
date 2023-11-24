package com.lian.pet.domain.dto;

import com.lian.pet.domain.page.BasePageQueryReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel("查询我的领养申请")
public class QueryMyApplyDTO extends BasePageQueryReq {
    
    @ApiModelProperty(value = "openId")
    private String openId;
}
