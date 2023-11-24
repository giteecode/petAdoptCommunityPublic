package com.lian.pet.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Desc:

 * @Time: 2022/1/13 14:27
 */
@Data
@ApiModel("小程序授权登录")
public class WxUserDTO {

    private String code;
    @ApiModelProperty(value = "用户昵称")
    private String nickname;
    @ApiModelProperty(value = "头像")
    private String avatarUrl;
    @ApiModelProperty(value = "城市")
    private String city;
    @ApiModelProperty(value = "国家")
    private String country;
    @ApiModelProperty(value = "省份")
    private String province;
    @ApiModelProperty(value = "性别")
    private String gender;
    @ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "邮箱")
    private String email;

}
