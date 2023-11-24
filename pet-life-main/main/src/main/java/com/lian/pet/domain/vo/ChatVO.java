package com.lian.pet.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @Desc:

 * @Time: 2022/2/9 10:00
 */
@Data
@Builder
@ApiModel("查询消息")
public class ChatVO {
    @ApiModelProperty(value = "消息ID")
    private Integer msgId;
    @ApiModelProperty(value = "消息内容")
    private String content;
    @ApiModelProperty(value = "消息拥有者")
    private String userId;
    @ApiModelProperty(value = "昵称")
    private String nickname;
    @ApiModelProperty(value = "头像")
    private String avatarUrl;
    @ApiModelProperty(value = "类型 user-用户 system-系统")
    private String type;
    @ApiModelProperty(value = "时间")
    private String time;

}
