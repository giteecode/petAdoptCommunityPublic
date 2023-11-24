package com.lian.pet.common.oss.aliyun.config.properties;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

/**
 * @Desc: 阿里云配置

 * @Time: 2022/1/26 10:31
 */
@Data
@ConfigurationProperties(prefix = "aliyun")
@PropertySource(value = {"classpath:oss-conf.properties"}, encoding = "UTF-8")
public class AliYunProperties {
    /**
     * @Autowired
     * 可指定 aliYunOssProperties 优先加载
     */
    @Autowired
    AliYunOssProperties aliYunOssProperties;
    private String accessKeyId;
    private String accessKeySecret;

    @Bean
    public OSSClient ossClient() {
        // 设置超时机制和重试机制
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(5000);
        conf.setMaxErrorRetry(3);
        return new OSSClient(aliYunOssProperties.getEndpoint(), accessKeyId, accessKeySecret, conf);
    }
}
