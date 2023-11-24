package com.lian.pet.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lian.pet.common.basic.enums.FinishStatusEnum;
import com.lian.pet.common.basic.utils.DateUtil;
import com.lian.pet.common.oss.aliyun.config.properties.AliYunOssProperties;
import com.lian.pet.constants.Constants;
import com.lian.pet.domain.dto.AddPetFindDTO;
import com.lian.pet.domain.dto.QueryPetFindDTO;
import com.lian.pet.domain.entity.PetFind;
import com.lian.pet.domain.entity.WxUser;
import com.lian.pet.domain.vo.*;
import com.lian.pet.factory.ResponseBeanFactory;
import com.lian.pet.mapper.PetFindMapper;
import com.lian.pet.mapper.WxUserMapper;
import com.lian.pet.service.PetFindService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Desc:

 * @Time: 2022/1/28 13:46
 */
@Slf4j
@AllArgsConstructor
@Service
public class PetFindServiceImpl implements PetFindService {
    private final PetFindMapper petFindMapper;
    private final AliYunOssProperties ossProperties;
    private final WxUserMapper wxUserMapper;

    @Override
    public void addPetFind(AddPetFindDTO req) {
        //PetFind petFind = ResponseBeanFactory.getPetFind(req, ossProperties.getUrlPrefix());
        PetFind petFind = ResponseBeanFactory.getPetFind(req, Constants.PREURL);
        petFindMapper.insert(petFind);
        log.info("执行成功[发布寻宠信息]");
    }

    @Override
    public List<PetFindVO> queryPetFindList(QueryPetFindDTO req) {
        String startTime = "";
        if ("1".equals(req.getUpdateTime())) {
            startTime = DateUtil.getPastDate(7);
        }else if ("2".equals(req.getUpdateTime())) {
            startTime = DateUtil.getPastDate(30);
        }else if ("3".equals(req.getUpdateTime())) {
            startTime = DateUtil.getPastDate(90);
        }
        IPage<PetFind> page = new Page<>(req.getPageNum(), req.getPageSize());
        IPage<PetFind> iPage = petFindMapper.selectPage(page, Wrappers.<PetFind>lambdaQuery()
                .eq(StringUtils.isNotBlank(req.getOpenId()), PetFind::getOpenId, req.getOpenId())
                .eq(StringUtils.isNotBlank(req.getCity()), PetFind::getCity, req.getCity())
                .eq(StringUtils.isNotBlank(req.getType()), PetFind::getType, req.getType())
                .eq(StringUtils.isNotBlank(req.getSex()), PetFind::getSex, req.getSex())
                .ge(StringUtils.isNotBlank(req.getUpdateTime()), PetFind::getUpdateTime, startTime)
                .le(StringUtils.isNotBlank(req.getUpdateTime()), PetFind::getUpdateTime, DateUtil.getCurrentTime())
                .orderByDesc(PetFind::getCreateTime));
        List<PetFindVO> petFindVOS = iPage.getRecords().stream()
                .map(PetFindVO::fromPetFind).collect(Collectors.toList());
        log.info("执行成功[查询寻宠列表]");
        return petFindVOS;
    }

    @Override
    public PetFindAndUserVO getPetFindById(Integer petFindId) {
        PetFind petFind = petFindMapper.selectById(petFindId);
        PetFind find = new PetFind();
        find.setId(petFindId);
        // 浏览量+1
        find.setView(petFind.getView() + 1);
        petFindMapper.updateById(find);
        // 送养人信息
        WxUser wxUser = wxUserMapper.selectOne(Wrappers.<WxUser>lambdaQuery().eq(WxUser::getOpenId, petFind.getOpenId()));
        // 正在送养数量
        Integer finishingCount = petFindMapper.selectCount(Wrappers.<PetFind>lambdaQuery()
                .eq(PetFind::getOpenId, wxUser.getOpenId())
                .eq(PetFind::getIsFinish, FinishStatusEnum.FINISHING.code()));
        // 已送养数量
        Integer finishedCount = petFindMapper.selectCount(Wrappers.<PetFind>lambdaQuery()
                .eq(PetFind::getOpenId, wxUser.getOpenId())
                .eq(PetFind::getIsFinish, FinishStatusEnum.FINISHED.code()));
        PetFindAndUserVO resultVO = PetFindAndUserVO.builder()
                .petFindVO(PetFindVO.fromPetFind(petFind))
                .wxUserVO(WxUserVO.fromWxUser(wxUser))
                .build();
        resultVO.getWxUserVO().setDoingNum(finishingCount);
        resultVO.getWxUserVO().setDoneNum(finishedCount);
        log.info("执行成功[查询寻宠详情]petFindId={}", petFindId);
        return resultVO;
    }

    @Override
    public Integer queryCount(String openId) {
        return petFindMapper.selectCount(Wrappers.<PetFind>lambdaQuery().eq(PetFind::getOpenId, openId));
    }
}
