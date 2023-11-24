package com.lian.pet.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Desc: 微信小程序配置

 * @Time: 2022/1/11 14:03
 */
@Data
@ConfigurationProperties(prefix = "wechat.applet")
public class WechatAppletProperties {
    private String appId;
    private String appSecret;
    private String accessTokenApiUrl;
}
