package com.lian.pet.controller;

import com.lian.pet.common.basic.exception.AppErrorEnum;
import com.lian.pet.common.basic.response.AppResp;
import com.lian.pet.domain.dto.AddApplyDTO;
import com.lian.pet.domain.dto.QueryApplyDTO;
import com.lian.pet.domain.vo.ApplyVO;
import com.lian.pet.service.ApplyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("apply")
@RestController
@AllArgsConstructor
public class ApplyController {
    private final ApplyService applyService;

    /**
     * 添加申请
     * @param req
     * @return
     */
    @PostMapping("/add")
    public AppResp<Void> addApply(@RequestBody AddApplyDTO req) {
        if (ObjectUtils.isEmpty(req.getOpenId())) {
            log.error("终止流程[openId为空]");
            return AppResp.failed(AppErrorEnum.BAD_REQUEST.getCode(), AppErrorEnum.BAD_REQUEST.getDefMsg());
        }
        applyService.addApply(req);
        return AppResp.succeed(null);
    }

    @PutMapping("/{id}")
    public AppResp<Void> checkApply(@PathVariable("id") Integer id,@RequestBody AddApplyDTO req ) {
        if (ObjectUtils.isEmpty(req.getOpenId())) {
            log.error("终止流程[openId为空]");
            return AppResp.failed(AppErrorEnum.BAD_REQUEST.getCode(), AppErrorEnum.BAD_REQUEST.getDefMsg());
        }
        applyService.checkApply(id,req);
        return AppResp.succeed(null);
    }

    /**
     * 查询申请列表
     * @param req
     * @return
     */
    @PostMapping("/queryList")
    public AppResp<List<ApplyVO>> queryApplys(@RequestBody QueryApplyDTO req) {
        return AppResp.succeed(applyService.queryApplyList(req));
    }

    @PostMapping("/queryMyList")
    public AppResp<List<ApplyVO>> queryMyList(@RequestBody QueryApplyDTO req) {
        return AppResp.succeed(applyService.queryMyApplyList(req));
    }

    @GetMapping("/{id}")
    public AppResp<ApplyVO> get(@PathVariable("id") Integer id) {
        return AppResp.succeed(applyService.get(id));
    }
}
