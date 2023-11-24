package com.lian.pet.domain.dto;

import com.lian.pet.domain.page.BasePageQueryReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import java.util.List;

/**
 * @Desc:

 * @Time: 2022/2/18 14:05
 */
@SuperBuilder
@Data
@ApiModel(value = "查询领养列表", description = "通过多个id查询")
public class QueryAdoptsInDTO extends BasePageQueryReq {
    @ApiModelProperty(value = "领养id")
    private List<Integer> ids;
}
