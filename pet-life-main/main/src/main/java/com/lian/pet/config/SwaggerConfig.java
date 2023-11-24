package com.lian.pet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Desc: Swagger配置

 * @Time: 2022/2/14 15:36
 */
//@Profile("dev")
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // swagger要扫描的包路径
                .apis(RequestHandlerSelectors.basePackage("com.lian.pet.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("系统接口文档")
                .description("接口文档")
//                .contact(new Contact("Swagger测试","localhost:8080/rms/swagger-ui.html","xxx@163.com"))
                .version("2.1")
                .build();
    }
}
