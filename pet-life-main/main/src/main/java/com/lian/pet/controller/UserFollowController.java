package com.lian.pet.controller;

import com.lian.pet.common.basic.response.AppResp;
import com.lian.pet.domain.dto.AddUserFollowDTO;
import com.lian.pet.domain.vo.UserFollowVO;
import com.lian.pet.service.UserFollowService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @Desc: UserFollow Controller

 * @Time: 2022/2/12 16:05
 */
@Slf4j
@RequestMapping("userFollow")
@RestController
@AllArgsConstructor
public class UserFollowController {
    private final UserFollowService userFollowService;

    /**
     * 添加用户关注
     * @param req
     * @return
     */
    @PostMapping("/addUserFollow")
    private AppResp<Boolean> addUserFollow(@RequestBody AddUserFollowDTO req) {
        return AppResp.succeed(userFollowService.addUserFollow(req));
    }

    /**
     * 取消关注
     * @param req
     * @return
     */
    @PostMapping("/unUserFollow")
    private AppResp<Boolean> unUserFollow(@RequestBody AddUserFollowDTO req) {
        return AppResp.succeed(userFollowService.unUserFollow(req));
    }

    /**
     * 查询是否关注
     * @param userId
     * @param followId
     * @return
     */
    @GetMapping("/isFollow")
    private AppResp<Boolean> isFollow(@RequestParam("userId") String userId, @RequestParam("followId") String followId) {
        return AppResp.succeed(userFollowService.isFollow(userId, followId));
    }

    /**
     * 查询用户关注列表
     * @param userId
     * @return
     */
    @GetMapping("/queryUserFollows")
    private AppResp<List<UserFollowVO>> queryUserFollows(@RequestParam("userId") String userId) {
        return AppResp.succeed(userFollowService.queryUserFollows(userId));
    }

}
