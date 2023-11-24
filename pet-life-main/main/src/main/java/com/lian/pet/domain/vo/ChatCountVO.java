package com.lian.pet.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @Desc:

 * @Time: 2022/2/9 9:57
 */
@Data
@Builder
@ApiModel("查询消息数量")
public class ChatCountVO {
    @ApiModelProperty(value = "消息数量")
    private Integer count;
    @ApiModelProperty(value = "最新消息")
    private String latestContent;
    @ApiModelProperty(value = "用户ID")
    private String userId;
    @ApiModelProperty(value = "昵称")
    private String nickname;
    @ApiModelProperty(value = "头像")
    private String avatarUrl;
    @ApiModelProperty(value = "时间")
    private String time;
}
