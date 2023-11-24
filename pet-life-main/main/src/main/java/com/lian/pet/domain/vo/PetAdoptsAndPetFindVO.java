package com.lian.pet.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import java.util.List;

/**
 * @Desc:

 * @Time: 2022/1/31 0:06
 */
@Data
@Builder
@ApiModel("查询用户领养/寻宠列表")
public class PetAdoptsAndPetFindVO {
    @ApiModelProperty(value = "宠物领养列表")
    private List<PetAdoptVO> petAdoptVOS;
    @ApiModelProperty(value = "寻宠列表")
    private List<PetFindVO> petFindVOS;
}
