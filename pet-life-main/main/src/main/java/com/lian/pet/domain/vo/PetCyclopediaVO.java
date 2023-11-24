package com.lian.pet.domain.vo;

import com.lian.pet.common.basic.utils.DateUtil;
import com.lian.pet.constants.Constants;
import com.lian.pet.domain.entity.PetCyclopedia;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.util.ObjectUtils;

/**
 * @Desc:

 * @Time: 2022/1/30 14:15
 */
@Data
@Builder
@ApiModel("查询宠物科普")
public class PetCyclopediaVO {
    private Integer id;
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "图片")
    private String picture;
    @ApiModelProperty(value = "内容(富文本)")
    private String content;
    @ApiModelProperty(value = "浏览量")
    private Integer view;
    @ApiModelProperty(value = "类型(1-标签 2-列表)")
    private String type;
    @ApiModelProperty(value = "标签背景颜色")
    private String backgroundColor;
    @ApiModelProperty(value = "更新时间")
    private String updateTime;

    public static PetCyclopediaVO fromPetCyclopedia(PetCyclopedia entity) {
        return PetCyclopediaVO.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .picture(Constants.PREURL+entity.getPicture())
                .content(entity.getContent())
                .view(entity.getView())
                .type(entity.getType())
                .backgroundColor(entity.getBackgroundColor())
                .updateTime(ObjectUtils.isEmpty(entity.getUpdateTime())
                        ? DateUtil.dateToString(entity.getCreateTime())
                        : DateUtil.dateToString(entity.getUpdateTime()))
                .build();
    }
}
