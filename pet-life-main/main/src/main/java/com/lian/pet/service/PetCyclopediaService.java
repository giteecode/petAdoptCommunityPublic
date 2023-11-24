package com.lian.pet.service;

import com.lian.pet.domain.dto.QueryPetCyclopediaDTO;
import com.lian.pet.domain.vo.PetCyclopediaVO;
import java.util.List;

/**
 * @Desc: Pet Cyclopedia Service

 * @Time: 2022/1/30 14:10
 */
public interface PetCyclopediaService {

    /**
     * 查询宠物科普列表
     * @param req
     * @return
     */
    List<PetCyclopediaVO> queryPetCyclopedias(QueryPetCyclopediaDTO req);

    /**
     * 查询宠物科普
     * @param id
     * @return
     */
    PetCyclopediaVO getPetCyclopedia(Integer id);
}
