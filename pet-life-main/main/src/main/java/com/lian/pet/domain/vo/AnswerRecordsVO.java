package com.lian.pet.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @Desc:

 * @Time: 2022/2/17 17:14
 */
@Data
@Builder
@ApiModel("查询答题记录")
public class AnswerRecordsVO {
    @ApiModelProperty(value = "答题ID")
    private Integer answerId;
    @ApiModelProperty(value = "题目")
    private String subject;
    @ApiModelProperty(value = "答题结果", notes = "1-答对 2-答错")
    private String type;
    @ApiModelProperty(value = "答题时间")
    private String time;
}
