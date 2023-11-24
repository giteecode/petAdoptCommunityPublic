package com.lian.pet.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Desc:

 * @Time: 2022/1/24 15:31
 */
@Data
@ApiModel("发布领养")
public class AddReportDTO {
    private String openId;
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "微信号")
    private String wechat;
    @ApiModelProperty(value = "举报内容")
    private String content;
    @ApiModelProperty(value = "图片")
    private List<String> pictures;
}
