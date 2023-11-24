package com.lian.pet.domain.dto;

import com.lian.pet.domain.page.BasePageQueryReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Desc:

 * @Time: 2022/2/3 16:14
 */
@Data
@ApiModel("查询文章")
public class QueryArticleDTO extends BasePageQueryReq {
    @ApiModelProperty(value = "文章标题")
    private String label;
}
