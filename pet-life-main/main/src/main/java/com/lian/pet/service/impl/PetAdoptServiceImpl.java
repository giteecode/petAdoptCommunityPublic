package com.lian.pet.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lian.pet.common.basic.enums.FinishStatusEnum;
import com.lian.pet.common.oss.aliyun.config.properties.AliYunOssProperties;
import com.lian.pet.constants.Constants;
import com.lian.pet.domain.dto.AddPetAdoptDTO;
import com.lian.pet.domain.dto.QueryAdoptDTO;
import com.lian.pet.domain.dto.QueryAdoptsInDTO;
import com.lian.pet.domain.entity.PetAdopt;
import com.lian.pet.domain.entity.WxUser;
import com.lian.pet.domain.vo.AdoptAndUserVO;
import com.lian.pet.domain.vo.PetAdoptVO;
import com.lian.pet.domain.vo.PetCountVO;
import com.lian.pet.domain.vo.WxUserVO;
import com.lian.pet.factory.ResponseBeanFactory;
import com.lian.pet.mapper.PetAdoptMapper;
import com.lian.pet.mapper.WxUserMapper;
import com.lian.pet.service.ApplyService;
import com.lian.pet.service.PetAdoptService;
import com.lian.pet.service.PetCircleService;
import com.lian.pet.service.PetFindService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Desc:

 * @Time: 2022/1/24 15:37
 */
@Slf4j
@AllArgsConstructor
@Service
public class PetAdoptServiceImpl implements PetAdoptService {
    private final PetAdoptMapper petAdoptMapper;
    private final AliYunOssProperties ossProperties;
    private final WxUserMapper wxUserMapper;
    private final PetFindService petFindService;

    private final PetCircleService petCircleService;

    private final ApplyService applyService;
    @Override
    public void addPetAdopt(AddPetAdoptDTO req) {
        //PetAdopt petAdopt = ResponseBeanFactory.getPetAdopt(req, ossProperties.getUrlPrefix());
        PetAdopt petAdopt = ResponseBeanFactory.getPetAdopt(req, Constants.PREURL);
        petAdoptMapper.insert(petAdopt);
        log.info("执行成功[发布宠物领养]");
    }

    @Override
    public List<PetAdoptVO> queryAdoptList(QueryAdoptDTO req) {
        IPage<PetAdopt> page = new Page<>(req.getPageNum(), req.getPageSize());
        IPage<PetAdopt> iPage = petAdoptMapper.selectPage(page, Wrappers.<PetAdopt>lambdaQuery()
                .eq(StringUtils.isNotBlank(req.getOpenId()), PetAdopt::getOpenId, req.getOpenId())
                .eq(StringUtils.isNotBlank(req.getCity()), PetAdopt::getCity, req.getCity())
                        .like(StringUtils.isNotBlank(req.getTitle()),PetAdopt::getTitle,req.getTitle())
                .or().like(StringUtils.isNotBlank(req.getTitle()),PetAdopt::getContent,req.getTitle())
                .orderByDesc(PetAdopt::getCreateTime));
        List<PetAdoptVO> petAdoptVOS = iPage.getRecords().stream()
                .map(PetAdoptVO::fromPetAdopt).collect(Collectors.toList());
        log.info("执行成功[查询领养列表]");
        return petAdoptVOS;
    }

    @Override
    public AdoptAndUserVO getPetAdoptById(Integer adoptId) {
        PetAdopt petAdopt = petAdoptMapper.selectById(adoptId);
        PetAdopt adopt = new PetAdopt();
        adopt.setId(adoptId);
        // 浏览量+1
        adopt.setView(petAdopt.getView() + 1);
        petAdoptMapper.updateById(adopt);
        // 送养人信息
        WxUser wxUser = wxUserMapper.selectOne(Wrappers.<WxUser>lambdaQuery().eq(WxUser::getOpenId, petAdopt.getOpenId()));
        // 正在送养数量
        Integer finishingCount = petAdoptMapper.selectCount(Wrappers.<PetAdopt>lambdaQuery()
                .eq(PetAdopt::getOpenId, wxUser.getOpenId())
                .eq(PetAdopt::getIsFinish, FinishStatusEnum.FINISHING.code()));
        // 已送养数量
        Integer finishedCount = petAdoptMapper.selectCount(Wrappers.<PetAdopt>lambdaQuery()
                .eq(PetAdopt::getOpenId, wxUser.getOpenId())
                .eq(PetAdopt::getIsFinish, FinishStatusEnum.FINISHED.code()));
        AdoptAndUserVO resultVO = AdoptAndUserVO.builder()
                .petAdoptVO(PetAdoptVO.fromPetAdopt(petAdopt))
                .wxUserVO(WxUserVO.fromWxUser(wxUser))
                .build();
        resultVO.getWxUserVO().setDoingNum(finishingCount);
        resultVO.getWxUserVO().setDoneNum(finishedCount);
        log.info("执行成功[查询领养详情]adoptId={}", adoptId);
        return resultVO;
    }

    @Override
    public PetCountVO queryCount(String openId) {
        Integer petAdoptCount = petAdoptMapper.selectCount(Wrappers.<PetAdopt>lambdaQuery().eq(PetAdopt::getOpenId, openId));
        Integer petFindCount = petFindService.queryCount(openId);
        Integer petCycleCount = petCircleService.queryCount(openId);
        Integer applyCount = applyService.queryCount(openId);
        Integer myApplyCount = applyService.queryMyCount(openId);
        log.info("执行成功[查询领养/寻宠总数],openId={}", openId);
        return PetCountVO.builder()
                .petAdoptCount(petAdoptCount)
                .petFindCount(petFindCount)
                .petCycleCount(petCycleCount)
                .petApplyCount(applyCount)
                .petMyApplyCount(myApplyCount)
                .build();
    }

    @Override
    public List<PetAdoptVO> queryAdoptsInIds(QueryAdoptsInDTO req) {
        if (req.getIds().isEmpty()) {
            return Collections.emptyList();
        }
        Page<PetAdopt> page = new Page<>(req.getPageNum(), req.getPageSize());
        Page<PetAdopt> iPage = petAdoptMapper.selectPage(page, Wrappers.<PetAdopt>lambdaQuery()
                .in(PetAdopt::getId, req.getIds()));
        List<PetAdopt> petAdopts = iPage.getRecords();
        if (petAdopts.isEmpty()) {
            return Collections.emptyList();
        }
        List<PetAdoptVO> petAdoptVOS = petAdopts.stream()
                .map(PetAdoptVO::fromPetAdopt).collect(Collectors.toList());
        log.info("执行成功[查询领养列表]");
        return petAdoptVOS;
    }
}
