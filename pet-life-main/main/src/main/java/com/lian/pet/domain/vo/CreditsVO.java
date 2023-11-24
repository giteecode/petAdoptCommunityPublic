package com.lian.pet.domain.vo;

import com.lian.pet.common.basic.utils.DateUtil;
import com.lian.pet.domain.entity.Credits;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.util.ObjectUtils;

/**
 * @Desc:

 * @Time: 2022/1/30 16:32
 */
@Data
@Builder
@ApiModel("查询积分")
public class CreditsVO {
    private Integer id;
    @ApiModelProperty(value = "用户ID")
    private String openId;
    @ApiModelProperty(value = "积分")
    private Integer creditsNum;
    @ApiModelProperty(value = "签到天数")
    private Integer dayNum;
    @ApiModelProperty(value = "更新时间")
    private String updateTime;
    @ApiModelProperty(value = "今日是否已签到")
    private Boolean isFinish;

    public static CreditsVO fromCreditsVO(Credits entity) {
        return CreditsVO.builder()
                .id(entity.getId())
                .openId(entity.getOpenId())
                .creditsNum(entity.getCreditsNum())
                .dayNum(entity.getDayNum())
                .updateTime(ObjectUtils.isEmpty(entity.getUpdateTime())
                        ? DateUtil.dateToString(entity.getCreateTime())
                        : DateUtil.dateToString(entity.getUpdateTime()))
                .build();
    }
}
