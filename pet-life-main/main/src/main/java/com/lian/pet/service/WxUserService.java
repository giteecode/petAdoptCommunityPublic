package com.lian.pet.service;

import com.lian.pet.domain.dto.UserAuthDTO;
import com.lian.pet.domain.dto.WxGetPhoneDTO;
import com.lian.pet.domain.dto.WxUserDTO;
import com.lian.pet.domain.vo.WxPhoneVO;
import com.lian.pet.domain.vo.WxUserVO;

/**
 * @Desc: WxUser Service

 * @Time: 2022/1/12 17:10
 */
public interface WxUserService {

    /**
     * 登录
     * @param code
     * @param req
     * @return
     */
    WxUserVO login(String code, WxUserDTO req);

    /**
     * 获取手机号
     * @param req
     * @return
     */
    WxPhoneVO getPhoneNumber(WxGetPhoneDTO req);

    /**
     * 查询用户信息
     * @param openId
     * @return
     */
    WxUserVO getUserInfo(String openId);

    /**
     * 手机号码登录
     * @param phone
     * @param password
     * @return
     */
    WxUserVO phoneLogin(String phone, String password);

    void userAuth(UserAuthDTO req);

    WxUserVO update(String openId,WxUserDTO req);
}
