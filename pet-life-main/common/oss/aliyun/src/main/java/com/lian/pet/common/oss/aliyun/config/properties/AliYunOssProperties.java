package com.lian.pet.common.oss.aliyun.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @Desc: 阿里云 oss 配置

 * @Time: 2022/1/26 10:35
 */
@Data
@ConfigurationProperties(prefix = "aliyun.oss")
@PropertySource(value = {"classpath:oss-conf.properties"}, encoding = "UTF-8")
public class AliYunOssProperties {
    private String endpoint;
    private String bucketName;
    private String urlPrefix;
}
