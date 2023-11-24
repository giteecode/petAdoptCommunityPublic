package com.lian.pet.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


@Data
@ApiModel("领养申请")
public class AddApplyDTO {
    private String openId;
    @ApiModelProperty(value = "关联领养宠物id")
    private Integer adoptId;
    private String sex;
    private String age;
    private String petExperience;
    private String petNow;
    private String house;
    private String marriage;
    private String monthlyIncome;
    private String work;
    private String province;
    private String city;
    private String status;
    @ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "微信号")
    private String wechat;
    @ApiModelProperty(value = "领养理由")
    private String content;
}
