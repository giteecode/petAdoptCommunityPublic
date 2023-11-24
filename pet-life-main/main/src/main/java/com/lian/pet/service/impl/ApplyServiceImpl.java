package com.lian.pet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lian.pet.common.basic.exception.AppException;
import com.lian.pet.common.oss.aliyun.config.properties.AliYunOssProperties;
import com.lian.pet.domain.dto.AddApplyDTO;
import com.lian.pet.domain.dto.AddReportDTO;
import com.lian.pet.domain.dto.QueryApplyDTO;
import com.lian.pet.domain.entity.Apply;
import com.lian.pet.domain.entity.PetAdopt;
import com.lian.pet.domain.entity.PetCircle;
import com.lian.pet.domain.entity.Report;
import com.lian.pet.domain.vo.ApplyVO;
import com.lian.pet.domain.vo.PetAdoptVO;
import com.lian.pet.factory.ResponseBeanFactory;
import com.lian.pet.mapper.ApplyMapper;
import com.lian.pet.mapper.PetAdoptMapper;
import com.lian.pet.mapper.ReportMapper;
import com.lian.pet.service.ApplyService;
import com.lian.pet.service.ReportService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class ApplyServiceImpl implements ApplyService {
    private final ApplyMapper applyMapper;
    private final PetAdoptMapper petAdoptMapper;

    @Override
    public void addApply(AddApplyDTO req) {
        Apply apply = ResponseBeanFactory.getApply(req);
        // 通过。将当前领养人id绑定到领养表
        LambdaQueryWrapper<Apply> wrapper = new LambdaQueryWrapper();
        wrapper.eq(Apply::getOpenId,req.getOpenId()).eq(Apply::getAdoptId,req.getAdoptId());
        if(applyMapper.selectCount(wrapper) > 0){
            throw new AppException("您已申请过,请勿重复提交");
        }
        PetAdopt petAdopt = petAdoptMapper.selectById(apply.getAdoptId());
        if(petAdopt == null){
            throw new AppException("当前领养宠物不存在");
        }
        if("0".equals(petAdopt.getIsFinish())){
            throw new AppException("当前领养宠物已送养");
        }
        if(petAdopt.getOpenId().equals(apply.getOpenId())){
            throw new AppException("送养人和领养人不能为同一个人");
        }
        apply.setOwnerOpenId(petAdopt.getOpenId());
        applyMapper.insert(apply);
        log.info("执行成功[添加领养申请]");
    }


    @Override
    public List<ApplyVO> queryApplyList(QueryApplyDTO req) {
        IPage<Apply> page = new Page<>(req.getPageNum(), req.getPageSize());
        IPage<Apply> iPage = applyMapper.selectPage(page, Wrappers.<Apply>lambdaQuery()
                .eq(StringUtils.isNotBlank(req.getOpenId()), Apply::getOwnerOpenId, req.getOpenId())
                .orderByDesc(Apply::getCreateTime));
        List<ApplyVO> applyVOS = iPage.getRecords().stream()
                .map(ApplyVO::fromApply).collect(Collectors.toList());
        applyVOS.forEach( applyVO -> {
          PetAdopt petAdopt =  petAdoptMapper.selectById(applyVO.getAdoptId());
          applyVO.setPetName(Optional.ofNullable(petAdopt.getTitle()).orElse(""));
        });
        log.info("执行成功[查询领养申请列表]");
        return applyVOS;
    }

    @Override
    public List<ApplyVO> queryMyApplyList(QueryApplyDTO req) {
        IPage<Apply> page = new Page<>(req.getPageNum(), req.getPageSize());
        IPage<Apply> iPage = applyMapper.selectPage(page, Wrappers.<Apply>lambdaQuery()
                .eq(StringUtils.isNotBlank(req.getOpenId()), Apply::getOpenId, req.getOpenId())
                .orderByDesc(Apply::getCreateTime));
        List<ApplyVO> applyVOS = iPage.getRecords().stream()
                .map(ApplyVO::fromApply).collect(Collectors.toList());
        applyVOS.forEach( applyVO -> {
            PetAdopt petAdopt =  petAdoptMapper.selectById(applyVO.getAdoptId());
            applyVO.setPetName(Optional.ofNullable(petAdopt.getTitle()).orElse(""));
        });
        log.info("执行成功[查询我的领养申请列表]");
        return applyVOS;
    }

    @Override
    public void checkApply(Integer id, AddApplyDTO req) {
        Apply apply = applyMapper.selectById(id);
        if(apply == null){
            throw new AppException("当前申请单不存在");
        }
        if(apply.getStatus() != null && !"0".equals(apply.getStatus())){
            throw new AppException("当前申请单已审核,请勿重复审核");
        }
        // 通过。将当前领养人id绑定到领养表
        PetAdopt petAdopt = petAdoptMapper.selectById(apply.getAdoptId());
        if(petAdopt == null){
            throw new AppException("当前领养宠物不存在");
        }
        if(!petAdopt.getOpenId().equals(req.getOpenId())){
            throw new AppException("您非宠物主人不得审核");
        }
        if("1".equals(req.getStatus())){
            if("0".equals(petAdopt.getIsFinish())){
                throw new AppException("当前领养宠物已送养");
            }
            petAdopt.setAdopterOpenId(apply.getOpenId());
            petAdopt.setUpdateTime(new Date());
            petAdopt.setIsFinish("0");
            petAdoptMapper.updateById(petAdopt);
        }else if ("2".equals(req.getStatus())){
        }else{
            throw new AppException("未知审核状态");
        }
        apply.setStatus(req.getStatus());
        apply.setUpdateTime(new Date());
        applyMapper.updateById(apply);
    }

    @Override
    public ApplyVO get(Integer id) {
        Apply apply = applyMapper.selectById(id);
        if(apply == null){
            throw new AppException("当前申请单不存在");
        }
        return ApplyVO.fromApply(apply);
    }

    @Override
    public Integer queryCount(String openId) {
        return applyMapper.selectCount(Wrappers.<Apply>lambdaQuery().eq(Apply::getOwnerOpenId, openId));
    }

    @Override
    public Integer queryMyCount(String openId) {
        return applyMapper.selectCount(Wrappers.<Apply>lambdaQuery().eq(Apply::getOpenId, openId));
    }

}
