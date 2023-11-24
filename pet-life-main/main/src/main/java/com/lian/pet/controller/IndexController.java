package com.lian.pet.controller;

import com.lian.pet.common.basic.response.AppResp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Desc: Index Controller
 * @Time: 2022/2/15 11:06
 */
@Slf4j
@RequestMapping("index")
@Controller
@RequiredArgsConstructor
public class IndexController {

    /**
     * 登录页面
     * @return
     */
    @GetMapping(value = "/login")
    public String test(){
        return "login.html";
    }

    /**
     * 上传微信审核时 设置为true
     * @return
     */
    @ResponseBody
    @GetMapping("/getFlag")
    public AppResp<Boolean> getFlag() {
        return AppResp.succeed(false);
    }
}
