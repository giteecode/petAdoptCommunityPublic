package com.lian.pet.controller;

import com.lian.pet.common.basic.exception.AppErrorEnum;
import com.lian.pet.common.basic.response.AppResp;
import com.lian.pet.domain.dto.UserAuthDTO;
import com.lian.pet.domain.dto.WxGetPhoneDTO;
import com.lian.pet.domain.dto.WxUserDTO;
import com.lian.pet.domain.vo.WxPhoneVO;
import com.lian.pet.domain.vo.WxUserVO;
import com.lian.pet.service.WxUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @Desc: User Controller

 * @Time: 2022/1/11 11:25
 */
@Slf4j
@RequestMapping("user")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final WxUserService wxUserService;

    @PostMapping("/login")
    public AppResp<WxUserVO> getOpenId(@RequestBody WxUserDTO req) {
        log.info("[==========进入user/login方法==========]");
        log.info("微信授权登录");
        log.info("code值：{}", req.getCode());
        WxUserVO wxUserVO = wxUserService.login(req.getCode(), req);
        return AppResp.succeed(wxUserVO);
    }

    /**
     * 获取手机号
     * @param req
     * @return
     */
    @PostMapping("/getPhoneNumber")
    public AppResp<WxPhoneVO> getPhone(@RequestBody WxGetPhoneDTO req) {
        WxPhoneVO wxPhoneVO = wxUserService.getPhoneNumber(req);
        return AppResp.succeed(wxPhoneVO);
    }

    /**
     * 查询用户信息 by openId
     * @param openId
     * @return
     */
    @GetMapping("/getUserInfo")
    public AppResp<WxUserVO> getPhone(@RequestParam String openId) {
        return AppResp.succeed(wxUserService.getUserInfo(openId));
    }

    /**
     * 手机号码登录
     * TODO 密码验证暂未做
     * @param phone
     * @param password
     * @return
     */
    @GetMapping("/phoneLogin")
    public AppResp<WxUserVO> phoneLogin(@RequestParam("phone") String phone,
                                        @RequestParam("password") String password) {
        return AppResp.succeed(wxUserService.phoneLogin(phone, password));
    }

    /**
     * 用户实名认证,上传身份证
     * @param req
     * @return
     */
    @PostMapping("/auth")
    public AppResp<Void> userAuth(@RequestBody UserAuthDTO req) {
        if (ObjectUtils.isEmpty(req.getOpenId())) {
            log.error("终止流程[openId为空]");
            return AppResp.failed(AppErrorEnum.BAD_REQUEST.getCode(), AppErrorEnum.BAD_REQUEST.getDefMsg());
        }
        wxUserService.userAuth(req);
        return AppResp.succeed(null);
    }

    @PutMapping("{openId}")
    public AppResp<WxUserVO> update(@PathVariable("openId") String openId ,@RequestBody WxUserDTO req) {
        if (ObjectUtils.isEmpty(openId)) {
            log.error("终止流程[openId为空]");
            return AppResp.failed(AppErrorEnum.BAD_REQUEST.getCode(), AppErrorEnum.BAD_REQUEST.getDefMsg());
        }
        WxUserVO userVO =  wxUserService.getUserInfo(openId);
        if (ObjectUtils.isEmpty(userVO)) {
            log.error("终止流程[用户信息为空]");
            return AppResp.failed(AppErrorEnum.BAD_REQUEST.getCode(), AppErrorEnum.BAD_REQUEST.getDefMsg());
        }
        return AppResp.succeed(wxUserService.update(openId,req));
    }

    
}
