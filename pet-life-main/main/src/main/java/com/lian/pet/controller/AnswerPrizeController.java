package com.lian.pet.controller;

import com.lian.pet.common.basic.response.AppResp;
import com.lian.pet.domain.dto.AddAnswerRecordDTO;
import com.lian.pet.domain.dto.QueryAnswerRecordsDTO;
import com.lian.pet.domain.vo.AnswerPrizeVO;
import com.lian.pet.domain.vo.AnswerRecordsVO;
import com.lian.pet.service.AnswerPrizeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @Desc: AnswerPrize Controller

 * @Time: 2022/2/17 14:50
 */
@Slf4j
@RequestMapping("answer")
@RestController
@RequiredArgsConstructor
public class AnswerPrizeController {
    private final AnswerPrizeService answerPrizeService;

    /**
     * 查询题目
     * @return
     */
    @GetMapping("getAnswerPrize")
    public AppResp<AnswerPrizeVO> getAnswerPrize(@RequestParam(value = "answerId", required = false) String answerId){
        return AppResp.succeed(answerPrizeService.getAnswerPrize(answerId));
    }

    /**
     * 用户答题 添加记录
     * 添加在表 collect_user_answer 中
     * @param req
     * @return
     */
    @PostMapping("addAnswerRecord")
    public AppResp<Boolean> addAnswerRecord(@RequestBody AddAnswerRecordDTO req){
        return AppResp.succeed(answerPrizeService.addCollectUserAnswer(req));
    }

    /**
     * 今日是否已答题
     * @param userId
     * @return
     *
     *    TODO
     *    0-表示今日未答题
     *    1-表示答过题且答对
     *    2-表示答过题且答错
     */
    @GetMapping("isFinishToday")
    public AppResp<String> isFinishToday(@RequestParam("userId") String userId){
        return AppResp.succeed(answerPrizeService.isFinishToday(userId));
    }

    /**
     * 查询答题记录
     * @param req
     * @return
     */
    @PostMapping("queryAnswerRecords")
    public AppResp<List<AnswerRecordsVO>> queryAnswerRecords(@RequestBody QueryAnswerRecordsDTO req){
        return AppResp.succeed(answerPrizeService.queryAnswerRecords(req));
    }

}
