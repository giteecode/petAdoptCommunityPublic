package com.lian.pet.controller;

import com.lian.pet.common.basic.exception.AppErrorEnum;
import com.lian.pet.common.basic.response.AppResp;
import com.lian.pet.domain.dto.AddPetAdoptDTO;
import com.lian.pet.domain.dto.AddReportDTO;
import com.lian.pet.domain.dto.QueryAdoptDTO;
import com.lian.pet.domain.vo.AdoptAndUserVO;
import com.lian.pet.domain.vo.PetAdoptVO;
import com.lian.pet.domain.vo.PetCountVO;
import com.lian.pet.service.PetAdoptService;
import com.lian.pet.service.ReportService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("report")
@RestController
@AllArgsConstructor
public class ReportController {
    private final ReportService reportService;

    /**
     * 添加举报
     * @param req
     * @return
     */
    @PostMapping("/add")
    public AppResp<Void> addReport(@RequestBody AddReportDTO req) {
        if (ObjectUtils.isEmpty(req.getOpenId())) {
            log.error("终止流程[openId为空]");
            return AppResp.failed(AppErrorEnum.BAD_REQUEST.getCode(), AppErrorEnum.BAD_REQUEST.getDefMsg());
        }
        reportService.addReport(req);
        return AppResp.succeed(null);
    }


}
