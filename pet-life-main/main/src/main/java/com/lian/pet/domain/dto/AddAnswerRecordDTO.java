package com.lian.pet.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Desc:

 * @Time: 2022/2/17 16:13
 */
@Data
@ApiModel("添加答题记录")
public class AddAnswerRecordDTO {
    @ApiModelProperty(value = "用户ID")
    private String userId;
    @ApiModelProperty(value = "答题ID")
    private Integer answerId;
    @ApiModelProperty(value = "类型", notes = "1-答对 2-答错")
    private String type;
}
