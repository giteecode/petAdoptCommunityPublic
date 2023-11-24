package com.lian.pet.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @Desc:

 * @Time: 2022/1/27 18:25
 */
@Data
@Builder
@ApiModel("")
public class AdoptAndUserVO {
    @ApiModelProperty(value = "领养对象")
    private PetAdoptVO petAdoptVO;
    @ApiModelProperty(value = "用户对象")
    private WxUserVO wxUserVO;

}
