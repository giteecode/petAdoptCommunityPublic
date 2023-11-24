package com.lian.pet.domain.vo;

import com.lian.pet.common.basic.utils.DateUtil;
import com.lian.pet.domain.entity.Apply;
import com.lian.pet.domain.entity.PetAdopt;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Data
@Builder
@ApiModel("查询领养")
public class ApplyVO {
    private Integer id;
    /**
     * 关联pet_adopt的id
     */
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
    private String phone;
    /**
     * 微信号
     */
    private String wechat;
    /**
     * 领养理由
     */
    private String content;

    private String openId;
    private String ownerOpenId;

    /**
     * 状态:0-待审核 1-通过 2-拒绝
     */
    private String status;
    @ApiModelProperty(value = "更新时间")
    private String updateTime;
    @ApiModelProperty(value = "宠物名称")
    private String petName;


    public static ApplyVO fromApply(Apply entity) {
        return ApplyVO.builder()
                .id(entity.getId())
                .adoptId(entity.getAdoptId())
                .sex(entity.getSex())
                .age(entity.getAge())
                .petExperience(entity.getPetExperience())
                .petNow(entity.getPetNow())
                .house(entity.getHouse())
                .marriage(entity.getMarriage())
                .monthlyIncome(entity.getMonthlyIncome())
                .work(entity.getWork())
                .phone(entity.getPhone())
                .wechat(entity.getWechat())
                .city(entity.getCity())
                .petExperience(entity.getPetExperience())
                .content(entity.getContent())
                .openId(entity.getOpenId())
                .ownerOpenId(entity.getOwnerOpenId())
                .status(entity.getStatus())
                .updateTime(ObjectUtils.isEmpty(entity.getUpdateTime())
                        ? DateUtil.dateToString(entity.getCreateTime())
                        : DateUtil.dateToString(entity.getUpdateTime()))
                .build();
    }

}
