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
@ApiModel("发布寻宠")
public class AddPetFindDTO {
    private String openId;
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "宠物昵称")
    private String nickname;
    @ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "宠物类型", notes = "1 猫咪, 2 狗狗")
    private String type;
    @ApiModelProperty(value = "宠物品种")
    private String breed;
    @ApiModelProperty(value = "宠物性别", notes = "1 男, 2 女")
    private String sex;
    @ApiModelProperty(value = "宠物年龄")
    private String age;
    @ApiModelProperty(value = "所在城市")
    private String city;
    @ApiModelProperty(value = "具体地址")
    private String address;
    @ApiModelProperty(value = "描述内容")
    private String content;
    @ApiModelProperty(value = "图片")
    private List<String> pictures;
}
