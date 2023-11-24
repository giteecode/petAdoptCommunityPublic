package com.lian.pet.service;

import com.lian.pet.domain.dto.AddPetCircleDTO;
import com.lian.pet.domain.dto.QueryPetCircleDTO;
import com.lian.pet.domain.vo.PetCircleVO;
import java.util.List;

/**
 * @Desc: Pet Circle Service

 * @Time: 2022/1/28 22:03
 */
public interface PetCircleService {

    /**
     * 新增动态
     * @param req
     */
    void addPetCircle(AddPetCircleDTO req);

    /**
     * 查询动态列表
     * @param req
     * @return
     */
    List<PetCircleVO> queryPetCircles(QueryPetCircleDTO req);

    /**
     * 查询动态
     * @param id
     * @return
     */
    PetCircleVO getPetCircle(String id);

    Integer queryCount(String openId);
}
