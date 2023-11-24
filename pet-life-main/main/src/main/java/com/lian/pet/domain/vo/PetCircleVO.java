package com.lian.pet.domain.vo;

import com.lian.pet.common.basic.utils.DateUtil;
import com.lian.pet.domain.entity.PetCircle;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.util.ObjectUtils;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Desc:

 * @Time: 2022/1/13 14:10
 */
@AllArgsConstructor
@Data
@Builder
@ApiModel("查询动态")
public class PetCircleVO {
    private Integer id;
    @ApiModelProperty(value = "内容")
    private String content;
    @ApiModelProperty(value = "图片")
    private List<String> pictures;
    @ApiModelProperty(value = "更新时间")
    private String updateTime;
    @ApiModelProperty(value = "用户ID")
    private String userId;
    @ApiModelProperty(value = "用户名")
    private String nickname;
    @ApiModelProperty(value = "用户头像")
    private String avatarUrl;

    public static PetCircleVO fromPetCircle(PetCircle entity, String nickname, String avatarUrl, String urlPrefix) {
        return PetCircleVO.builder()
                .id(entity.getId())
                .content(entity.getContent())
                .pictures(Arrays.asList(entity.getPictures().replace("\"", "").split(","))
                        .stream().map(a -> a = urlPrefix.concat(a)).collect(Collectors.toList()))
                .updateTime(ObjectUtils.isEmpty(entity.getUpdateTime())
                        ? DateUtil.dateToString(entity.getCreateTime())
                        : DateUtil.dateToString(entity.getUpdateTime()))
                .userId(entity.getUserId())
                .nickname(nickname)
                .avatarUrl(avatarUrl)
                .build();
    }

    public static PetCircleVO fromPetCircle2(PetCircle entity, String nickname, String avatarUrl, String urlPrefix) {
        return PetCircleVO.builder()
                .id(entity.getId())
                .content(entity.getContent())
                .pictures(Arrays.asList(entity.getPictures().replace("\"", "").split(","))
                        .stream().map(a -> a = urlPrefix.concat(a)).collect(Collectors.toList()))
                .updateTime(ObjectUtils.isEmpty(entity.getUpdateTime())
                        ? DateUtil.dateToString(entity.getCreateTime())
                        : DateUtil.dateToString(entity.getUpdateTime()))
                .userId(entity.getUserId())
                .nickname(nickname)
                .avatarUrl(avatarUrl)
                .build();
    }

}
