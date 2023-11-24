package com.lian.pet.domain.dto.cache;

import com.lian.pet.domain.vo.PetAdoptVO;
import com.lian.pet.domain.vo.PetCircleVO;
import com.lian.pet.domain.vo.PetFindVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.util.List;

/**
 * @Desc: 存储到缓存中的数据对象

 * @Time: 2022/2/14 17:02
 */
@AllArgsConstructor
@Data
@Builder
public class CacheList {
    private List<PetCircleVO> petCircleVOS;
    private List<PetAdoptVO> petAdoptVOS;
    private List<PetFindVO> petFindVOS;
}
