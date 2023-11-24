package com.lian.pet.service;

import com.lian.pet.domain.dto.AddPetFindDTO;
import com.lian.pet.domain.dto.QueryPetFindDTO;
import com.lian.pet.domain.vo.PetFindAndUserVO;
import com.lian.pet.domain.vo.PetFindVO;
import java.util.List;

/**
 * @Desc: Pet Find Service

 * @Time: 2022/1/24 15:29
 */
public interface PetFindService {

    /**
     * 添加寻宠信息
     * @param req
     */
    void addPetFind(AddPetFindDTO req);

    /**
     * 查询寻宠列表
     * @param req
     * @return
     */
    List<PetFindVO> queryPetFindList(QueryPetFindDTO req);

    /**
     * 获取寻宠信息
     * @param petFindId
     * @return
     */
    PetFindAndUserVO getPetFindById(Integer petFindId);

    /**
     * 查询寻宠总数
     * @param openId
     * @return
     */
    Integer queryCount(String openId);

}
