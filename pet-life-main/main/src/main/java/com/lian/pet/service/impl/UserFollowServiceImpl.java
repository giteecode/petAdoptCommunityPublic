package com.lian.pet.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lian.pet.domain.dto.AddUserFollowDTO;
import com.lian.pet.domain.entity.UserFollow;
import com.lian.pet.domain.entity.WxUser;
import com.lian.pet.domain.vo.UserFollowVO;
import com.lian.pet.factory.ResponseBeanFactory;
import com.lian.pet.mapper.UserFollowMapper;
import com.lian.pet.mapper.WxUserMapper;
import com.lian.pet.service.UserFollowService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Desc:

 * @Time: 2022/2/12 15:53
 */
@Slf4j
@Service
@AllArgsConstructor
public class UserFollowServiceImpl implements UserFollowService {
    private final UserFollowMapper userFollowMapper;
    private final WxUserMapper wxUserMapper;

    @Override
    public Boolean addUserFollow(AddUserFollowDTO req) {
        UserFollow userFollow = ResponseBeanFactory.getUserFollow(req);
        userFollowMapper.insert(userFollow);
        log.info("执行成功[添加用户关注]");
        return true;
    }

    @Override
    public Boolean unUserFollow(AddUserFollowDTO req) {
        userFollowMapper.delete(Wrappers.<UserFollow>lambdaQuery()
                .eq(UserFollow::getUserId, req.getUserId())
                .eq(UserFollow::getFollowId, req.getFollowId()));
        return true;
    }

    @Override
    public Boolean isFollow(String userId, String followId) {
        List<UserFollow> userFollows = userFollowMapper.selectList(Wrappers.<UserFollow>lambdaQuery()
                .eq(UserFollow::getUserId, userId)
                .eq(UserFollow::getFollowId, followId));
        return !userFollows.isEmpty();
    }

    @Override
    public List<UserFollowVO> queryUserFollows(String userId) {
        List<UserFollow> userFollows = userFollowMapper.selectList(Wrappers.<UserFollow>lambdaQuery().eq(UserFollow::getUserId, userId));
        if (userFollows.isEmpty()) {
            log.info("该用户未关注他人,userId={}", userId);
            return Collections.emptyList();
        }
        List<UserFollowVO> userFollowVOS = new ArrayList<>();
        userFollows.forEach(userFollow -> {
            WxUser wxUser = wxUserMapper.selectOne(Wrappers.<WxUser>lambdaQuery().eq(WxUser::getOpenId, userFollow.getFollowId()));
            UserFollowVO userFollowVO = UserFollowVO.fromUserFollow(userFollow, wxUser);
            userFollowVOS.add(userFollowVO);
        });
        log.info("执行成功[查询用户关注列表]");
        return userFollowVOS;
    }
}
