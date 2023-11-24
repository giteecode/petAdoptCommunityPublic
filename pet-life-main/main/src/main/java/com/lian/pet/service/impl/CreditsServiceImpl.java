package com.lian.pet.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lian.pet.common.basic.utils.DateUtil;
import com.lian.pet.domain.entity.Credits;
import com.lian.pet.domain.vo.CreditsVO;
import com.lian.pet.factory.ResponseBeanFactory;
import com.lian.pet.mapper.CreditsMapper;
import com.lian.pet.service.CreditsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * @Desc:

 * @Time: 2022/1/30 16:37
 */
@Slf4j
@Service
@AllArgsConstructor
public class CreditsServiceImpl implements CreditsService {
    private final CreditsMapper creditsMapper;

    @Override
    public CreditsVO getCredits(String openId) {
        Credits credits = creditsMapper.selectOne(Wrappers.<Credits>lambdaQuery().eq(Credits::getOpenId, openId));
        if (ObjectUtils.isEmpty(credits)) {
            log.info("用户首次进入签到页面");
            return null;
        }
        log.info("执行成功[查询积分]");
        CreditsVO creditsVO = CreditsVO.fromCreditsVO(credits);
        // 今日是否打卡
        creditsVO.setIsFinish(DateUtil.getCurrentTime().substring(0, 10)
                .equals(creditsVO.getUpdateTime().substring(0, 10)));
        return creditsVO;
    }

    @Override
    public Integer updateCredits(String openId, Integer creditsNum) {
        Credits credits = creditsMapper.selectOne(Wrappers.<Credits>lambdaQuery().eq(Credits::getOpenId, openId));
        if (ObjectUtils.isEmpty(credits)) {
            // 首次签到 积分为0 天数为1
            creditsMapper.insert(ResponseBeanFactory.getCredits(openId, 0, 1));
        }else {
            credits.setDayNum(credits.getDayNum() + 1);
            credits.setCreditsNum(credits.getCreditsNum() + creditsNum);
            creditsMapper.updateById(credits);
        }
        log.info("执行成功[签到打卡]");
        return 1;
    }
}
