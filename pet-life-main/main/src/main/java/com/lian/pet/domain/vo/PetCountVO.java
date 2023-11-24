package com.lian.pet.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @Desc:

 * @Time: 2022/1/31 1:25
 */
@Data
@Builder
@ApiModel("查询领养/寻宠总数/宠物社区总数/收到领养申请总数/我的领养申请总数")
public class PetCountVO {
    @ApiModelProperty(value = "领养总数")
    private Integer petAdoptCount;
    @ApiModelProperty(value = "寻宠总数")
    private Integer petFindCount;
    @ApiModelProperty(value = "宠物社区总数")
    private Integer petCycleCount;
    @ApiModelProperty(value = "收到领养申请总数")
    private Integer petApplyCount;
    @ApiModelProperty(value = "我的领养申请总数")
    private Integer petMyApplyCount;
}
