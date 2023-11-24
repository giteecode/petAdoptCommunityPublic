package com.lian.pet.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lian.pet.common.basic.enums.FinishStatusEnum;
import com.lian.pet.common.oss.aliyun.config.properties.AliYunOssProperties;
import com.lian.pet.constants.Constants;
import com.lian.pet.domain.dto.AddPetAdoptDTO;
import com.lian.pet.domain.dto.AddReportDTO;
import com.lian.pet.domain.dto.QueryAdoptDTO;
import com.lian.pet.domain.dto.QueryAdoptsInDTO;
import com.lian.pet.domain.entity.PetAdopt;
import com.lian.pet.domain.entity.Report;
import com.lian.pet.domain.entity.WxUser;
import com.lian.pet.domain.vo.AdoptAndUserVO;
import com.lian.pet.domain.vo.PetAdoptVO;
import com.lian.pet.domain.vo.PetCountVO;
import com.lian.pet.domain.vo.WxUserVO;
import com.lian.pet.factory.ResponseBeanFactory;
import com.lian.pet.mapper.PetAdoptMapper;
import com.lian.pet.mapper.ReportMapper;
import com.lian.pet.mapper.WxUserMapper;
import com.lian.pet.service.PetAdoptService;
import com.lian.pet.service.PetFindService;
import com.lian.pet.service.ReportService;
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
public class ReportServiceImpl implements ReportService {
    private final ReportMapper reportMapper;
    private final AliYunOssProperties ossProperties;


    @Override
    public void addReport(AddReportDTO req) {
        Report report = ResponseBeanFactory.getReport(req, Constants.PREURL);
        reportMapper.insert(report);
        log.info("执行成功[添加举报]");
    }
}
