package com.lian.pet.domain.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("用户实名认证")
public class UserAuthDTO {
    private String openId;
    private String idcardFrontPic;
    private String idcardBackPic;
}
