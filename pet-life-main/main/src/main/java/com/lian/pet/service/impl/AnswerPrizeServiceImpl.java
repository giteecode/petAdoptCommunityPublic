package com.lian.pet.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lian.pet.common.basic.utils.DateUtil;
import com.lian.pet.domain.dto.AddAnswerRecordDTO;
import com.lian.pet.domain.dto.QueryAnswerRecordsDTO;
import com.lian.pet.domain.entity.AnswerPrize;
import com.lian.pet.domain.entity.CollectUserAnswer;
import com.lian.pet.domain.vo.AnswerPrizeVO;
import com.lian.pet.domain.vo.AnswerRecordsVO;
import com.lian.pet.mapper.AnswerPrizeMapper;
import com.lian.pet.mapper.CollectUserAnswerMapper;
import com.lian.pet.service.AnswerPrizeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Desc:

 * @Time: 2022/2/17 14:44
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AnswerPrizeServiceImpl implements AnswerPrizeService {
    private final AnswerPrizeMapper answerPrizeMapper;
    private final CollectUserAnswerMapper collectUserAnswerMapper;

    @Override
    public AnswerPrizeVO getAnswerPrize(String answerId) {
        List<AnswerPrize> answerPrizes = answerPrizeMapper.selectList(Wrappers.<AnswerPrize>lambdaQuery()
                .eq(StringUtils.isNotBlank(answerId), AnswerPrize::getId, answerId)
                .ge(StringUtils.isBlank(answerId), AnswerPrize::getCreateTime, DateUtil.getCurrentTime().substring(0, 11).concat("00:00:00"))
                .le(StringUtils.isBlank(answerId), AnswerPrize::getCreateTime, DateUtil.getCurrentTime().substring(0, 11).concat("23:59:59"))
                .orderByDesc(AnswerPrize::getCreateTime));
        if (answerPrizes.isEmpty()) {
            return null;
        }
        log.info("执行成功[查询题目]");
        return AnswerPrizeVO.fromAnswerPrize(answerPrizes.get(0));
    }

    @Override
    public Boolean addCollectUserAnswer(AddAnswerRecordDTO req) {
        CollectUserAnswer collectUserAnswer = new CollectUserAnswer();
        collectUserAnswer.setUserId(req.getUserId()).setAnswerId(req.getAnswerId()).setType(req.getType());
        collectUserAnswerMapper.insert(collectUserAnswer);
        return true;
    }

    @Override
    public String isFinishToday(String userId) {
        List<CollectUserAnswer> collectUserAnswers = collectUserAnswerMapper.selectList(Wrappers.<CollectUserAnswer>lambdaQuery()
                .eq(CollectUserAnswer::getUserId, userId)
                .ge(CollectUserAnswer::getCreateTime, DateUtil.getCurrentTime().substring(0, 11).concat("00:00:00"))
                .le(CollectUserAnswer::getCreateTime, DateUtil.getCurrentTime().substring(0, 11).concat("23:59:59")));
        if (collectUserAnswers.isEmpty()) {
            return "0";
        }
        if ("1".equals(collectUserAnswers.get(0).getType())) {
            return "1";
        }else {
            return "2";
        }
    }

    @Override
    public List<AnswerRecordsVO> queryAnswerRecords(QueryAnswerRecordsDTO req) {
        IPage<CollectUserAnswer> page = new Page<>(req.getPageNum(), req.getPageSize());
        IPage<CollectUserAnswer> iPage = collectUserAnswerMapper.selectPage(page, Wrappers.<CollectUserAnswer>lambdaQuery()
                .eq(CollectUserAnswer::getUserId, req.getUserId()));
        List<CollectUserAnswer> collectUserAnswers = iPage.getRecords();
        if (collectUserAnswers.isEmpty()) {
            return Collections.emptyList();
        }
        List<AnswerRecordsVO> answerRecordsVOS = new ArrayList<>();
        collectUserAnswers.forEach(collectUserAnswer -> {
            AnswerPrize answerPrize = answerPrizeMapper.selectById(collectUserAnswer.getAnswerId());
            answerRecordsVOS.add(AnswerRecordsVO.builder()
                    .subject(answerPrize.getSubject())
                    .answerId(collectUserAnswer.getAnswerId())
                    .type(collectUserAnswer.getType())
                    .time(DateUtil.dateToString(collectUserAnswer.getCreateTime()).substring(0, 10))
                    .build());
        });
        log.info("执行成功[查询答题记录],userId={}", req.getUserId());
        return answerRecordsVOS;
    }
}
