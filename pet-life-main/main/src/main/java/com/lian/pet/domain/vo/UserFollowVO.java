package com.lian.pet.domain.vo;

import com.lian.pet.domain.entity.UserFollow;
import com.lian.pet.domain.entity.WxUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @Desc:

 * @Time: 2022/2/12 15:43
 */
@Builder
@Data
@ApiModel("关注的好友")
public class UserFollowVO {
    @ApiModelProperty(value = "用户ID")
    private String userId;
    @ApiModelProperty(value = "用户昵称")
    private String nickname;
    @ApiModelProperty(value = "用户头像")
    private String avatarUrl;

    public static UserFollowVO fromUserFollow(UserFollow userFollow, WxUser wxUser) {
        return UserFollowVO.builder()
                .userId(userFollow.getFollowId())
                .nickname(wxUser.getNickName())
                .avatarUrl(wxUser.getAvatarUrl())
                .build();
    }
}
