package com.lian.pet.service;

import com.lian.pet.domain.vo.CreditsVO;

/**
 * @Desc:

 * @Time: 2022/1/30 16:32
 */
public interface CreditsService {

    /**
     * 查询积分
     * @param openId
     * @return
     */
    CreditsVO getCredits(String openId);

    /**
     * 积分修改
     * @param openId
     * @param creditsNum
     */
    Integer updateCredits(String openId, Integer creditsNum);
}
