package com.lian.pet.controller;

import com.lian.pet.common.basic.exception.AppErrorEnum;
import com.lian.pet.common.basic.response.AppResp;
import com.lian.pet.domain.dto.AddPetAdoptDTO;
import com.lian.pet.domain.dto.QueryAdoptDTO;
import com.lian.pet.domain.vo.AdoptAndUserVO;
import com.lian.pet.domain.vo.PetAdoptVO;
import com.lian.pet.domain.vo.PetCountVO;
import com.lian.pet.service.PetAdoptService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @Desc: Pet Adopt Controller

 * @Time: 2022/1/24 15:28
 */
@Slf4j
@RequestMapping("adopt")
@RestController
@AllArgsConstructor
public class PetAdoptController {
    private final PetAdoptService petAdoptService;

    /**
     * 添加宠物领养
     * @param req
     * @return
     */
    @PostMapping("/add")
    public AppResp<Void> addPetAdopt(@RequestBody AddPetAdoptDTO req) {
        if (ObjectUtils.isEmpty(req.getOpenId())) {
            log.error("终止流程[openId为空]");
            return AppResp.failed(AppErrorEnum.BAD_REQUEST.getCode(), AppErrorEnum.BAD_REQUEST.getDefMsg());
        }
        petAdoptService.addPetAdopt(req);
        return AppResp.succeed(null);
    }

    /**
     * 查询宠物领养列表
     * @param req
     * @return
     */
    @PostMapping("/queryList")
    public AppResp<List<PetAdoptVO>> queryPetAdopts(@RequestBody QueryAdoptDTO req) {
        return AppResp.succeed(petAdoptService.queryAdoptList(req));
    }

    /**
     * 查询宠物领养信息
     * @param adoptId
     * @return
     */
    @GetMapping("/getById")
    public AppResp<AdoptAndUserVO> getPetAdoptById(@RequestParam("adoptId") Integer adoptId) {
        return AppResp.succeed(petAdoptService.getPetAdoptById(adoptId));
    }

    /**
     * 查询宠物领养/寻宠总数
     * @param openId
     * @return
     */
    @GetMapping("/queryCount")
    public AppResp<PetCountVO> queryCount(@RequestParam("openId") String openId) {
        return AppResp.succeed(petAdoptService.queryCount(openId));
    }

}
