package com.lian.pet;

import com.lian.pet.config.properties.WechatAppletProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@MapperScan("com.lian.pet.mapper")
@EnableConfigurationProperties({WechatAppletProperties.class})
@SpringBootApplication
public class PetApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetApplication.class, args);
    }

}
