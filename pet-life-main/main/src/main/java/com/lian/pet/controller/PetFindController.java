package com.lian.pet.controller;

import com.lian.pet.common.basic.response.AppResp;
import com.lian.pet.domain.dto.AddPetFindDTO;
import com.lian.pet.domain.dto.QueryPetFindDTO;
import com.lian.pet.domain.vo.PetFindAndUserVO;
import com.lian.pet.domain.vo.PetFindVO;
import com.lian.pet.service.PetFindService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @Desc: Pet Find Controller

 * @Time: 2022/1/24 15:28
 */
@Slf4j
@RequestMapping("petFind")
@RestController
@AllArgsConstructor
public class PetFindController {
    private final PetFindService petFindService;

    /**
     * 添加寻宠信息
     * @param req
     * @return
     */
    @PostMapping("/add")
    public AppResp<Void> addPetAdopt(@RequestBody AddPetFindDTO req) {
        if (ObjectUtils.isEmpty(req.getOpenId())) {
            log.error("终止流程[openId为空]");
            // TODO code值统一定义管理
            return AppResp.failed(104, "openId为空！！");
        }
        petFindService.addPetFind(req);
        return AppResp.succeed(null);
    }

    /**
     * 查询寻宠列表
     * @param req
     * @return
     */
    @PostMapping("/queryList")
    public AppResp<List<PetFindVO>> queryPetFinds(@RequestBody QueryPetFindDTO req) {
        return AppResp.succeed(petFindService.queryPetFindList(req));
    }

    /**
     * 查询寻宠信息
     * @param petFindId
     * @return
     */
    @GetMapping("/getById")
    public AppResp<PetFindAndUserVO> getPetFindById(@RequestParam("adoptId") Integer petFindId) {
        return AppResp.succeed(petFindService.getPetFindById(petFindId));
    }

}
