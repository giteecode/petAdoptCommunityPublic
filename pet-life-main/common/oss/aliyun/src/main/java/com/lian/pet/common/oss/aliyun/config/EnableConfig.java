package com.lian.pet.common.oss.aliyun.config;

import com.lian.pet.common.oss.aliyun.config.properties.AliYunOssProperties;
import com.lian.pet.common.oss.aliyun.config.properties.AliYunProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Desc: 激活配置类

 * @Time: 2022/1/26 14:08
 */
@Configuration
@EnableConfigurationProperties({AliYunOssProperties.class, AliYunProperties.class})
public class EnableConfig {
}
