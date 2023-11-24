package com.lian.pet.domain.page;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @Desc: 分页

 * @Time: 2022/1/25 18:32
 */
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasePageQueryReq {
    @ApiModelProperty("分页号 默认:1")
    public Integer pageNum = 1;
    @ApiModelProperty("每页大小 默认10")
    public Integer pageSize = 10;

}
