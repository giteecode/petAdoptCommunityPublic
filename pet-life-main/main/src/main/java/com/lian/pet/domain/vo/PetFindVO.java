package com.lian.pet.domain.vo;

import com.lian.pet.common.basic.utils.DateUtil;
import com.lian.pet.domain.entity.PetFind;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.util.ObjectUtils;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Desc:

 * @Time: 2022/1/25 18:25
 */
@Data
@Builder
@ApiModel("查询寻宠")
public class PetFindVO {
    private Integer id;
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
    @ApiModelProperty(value = "是否完成（0-已完成 1-进行中）")
    private String isFinish;
    @ApiModelProperty(value = "浏览量")
    private Integer view;
    @ApiModelProperty(value = "更新时间")
    private String updateTime;

    public static PetFindVO fromPetFind(PetFind entity) {
        return PetFindVO.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .nickname(entity.getNickname())
                .phone(entity.getPhone())
                .type(entity.getType())
                .breed(entity.getBread())
                .sex(entity.getType())
                .age(entity.getAge())
                .city(entity.getCity())
                .address(entity.getAddress())
                .content(entity.getContent())
                .pictures(Arrays.asList(entity.getPictures().replace("\"", "").split(","))
                        .stream().map(a -> a = entity.getPicPrefix().concat(a)).collect(Collectors.toList()))
                .view(entity.getView())
                .isFinish(entity.getIsFinish())
                .updateTime(ObjectUtils.isEmpty(entity.getUpdateTime())
                        ? DateUtil.dateToString(entity.getCreateTime())
                        : DateUtil.dateToString(entity.getUpdateTime()))
                .build();
    }

}
