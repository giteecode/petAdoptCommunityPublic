package com.lian.pet.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Desc:

 * @Time: 2022/2/9 9:50
 */
@Data
@ApiModel("新增消息")
public class AddChatDTO {
    @ApiModelProperty(value = "发送人id")
    private String fromId;
    @ApiModelProperty(value = "接收人id")
    private String toId;
    @ApiModelProperty(value = "消息内容")
    private String content;
}
