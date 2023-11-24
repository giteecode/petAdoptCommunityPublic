package com.lian.pet.controller;

import com.lian.pet.common.basic.exception.AppErrorEnum;
import com.lian.pet.common.basic.response.AppResp;
import com.lian.pet.domain.dto.AddCollectDTO;
import com.lian.pet.domain.dto.QueryCollectDTO;
import com.lian.pet.service.CollectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @Desc: Collect Controller

 * @Time: 2022/2/18 14:20
 */
@Slf4j
@RequestMapping("collect")
@RestController
@RequiredArgsConstructor
public class CollectController {
    private final CollectService collectService;

    /**
     * 添加收藏
     * @param req
     * @return
     */
    @PostMapping("addCollect")
    public AppResp<Boolean> addCollect(@RequestBody AddCollectDTO req) {
        if (collectService.addCollect(req)) {
            return AppResp.succeed(true);
        }
        return AppResp.failed(AppErrorEnum.DUPLICATE_SUBMIT.getCode(), "请勿重复收藏");
    }

    /**
     * 取消收藏
     * @param req
     * @return
     */
    @PostMapping("removeCollect")
    public AppResp<Boolean> removeCollect(@RequestBody AddCollectDTO req) {
        return AppResp.succeed(collectService.removeCollect(req));
    }

    /**
     * 是否已收藏
     * @param req
     * @return
     */
    @PostMapping("isCollect")
    public AppResp<Boolean> isCollect(@RequestBody AddCollectDTO req) {
        return AppResp.succeed(collectService.isCollect(req));
    }

    /**
     * 查询收藏
     * @param req
     * @param <T>
     * @return
     */
    @PostMapping("queryCollect")
    public <T> AppResp<List<T>> queryCollect(@RequestBody QueryCollectDTO req) {
        return AppResp.succeed(collectService.queryCollect(req));
    }
}
