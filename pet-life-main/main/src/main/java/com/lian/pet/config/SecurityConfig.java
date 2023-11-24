package com.lian.pet.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Desc: Security配置

 * @Time: 2022/1/11 14:56
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .formLogin() // 表单登录。跳转到security默认的登录表单页
            .loginPage("/index/login")
            // http.httpBasic() //basic登录
            .and()
            .authorizeRequests() // 对请求授权
            .antMatchers(
                    // 放行Swagger start
                    "/swagger-ui.html",
                    "/webjars/**",
                    "/v2/**",
                    "/swagger-resources/**",
                    // 放行Swagger end
                    "/index/**",
                    "/user/**",
                    "/adopt/**",
                    "/file/**",
                    "/petFind/**",
                    "/petCircle/**",
                    "/petCyclopedia/**",
                    "/credits/**",
                    "/comment/**",
                    "/article/**",
                    "/chat/**",
                    "/userFollow/**",
                    "/answer/**",
                    "/collect/**",
                    "/report/**",
                    "/apply/**","/profile/**")
            .permitAll()
            .anyRequest() // 任何请求
            .authenticated() // 需要身份认证
        ;
    }

}
