package com.lian.pet.service;

import com.lian.pet.domain.dto.AddApplyDTO;
import com.lian.pet.domain.dto.QueryApplyDTO;
import com.lian.pet.domain.vo.ApplyVO;

import java.util.List;


public interface ApplyService {

    /**
     * 添加领养申请
     * @param req
     */
    void addApply(AddApplyDTO req);


    List<ApplyVO> queryApplyList(QueryApplyDTO req);

    List<ApplyVO> queryMyApplyList(QueryApplyDTO req);

    void checkApply(Integer id, AddApplyDTO req);

    ApplyVO get(Integer id);

    Integer queryCount(String openId);
    Integer queryMyCount(String openId);
}
