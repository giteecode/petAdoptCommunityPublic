package com.lian.pet.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lian.pet.domain.dto.QueryPetCyclopediaDTO;
import com.lian.pet.domain.entity.PetAdopt;
import com.lian.pet.domain.entity.PetCyclopedia;
import com.lian.pet.domain.vo.PetCyclopediaVO;
import com.lian.pet.mapper.PetCyclopediaMapper;
import com.lian.pet.service.PetCyclopediaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Desc:

 * @Time: 2022/1/30 14:21
 */
@Slf4j
@Service
@AllArgsConstructor
public class PetCyclopediaServiceImpl implements PetCyclopediaService {
    private final PetCyclopediaMapper petCyclopediaMapper;

    @Override
    public List<PetCyclopediaVO> queryPetCyclopedias(QueryPetCyclopediaDTO req) {
        IPage<PetCyclopedia> page = new Page<>(req.getPageNum(), req.getPageSize());
        IPage<PetCyclopedia> iPage = petCyclopediaMapper.selectPage(page, Wrappers.<PetCyclopedia>lambdaQuery()
                .orderByDesc(PetCyclopedia::getUpdateTime));
        List<PetCyclopedia> petCyclopedias = iPage.getRecords();
        if (petCyclopedias.isEmpty()) {
            return null;
        }
        List<PetCyclopediaVO> petCyclopediaVOS = petCyclopedias.stream()
                .map(PetCyclopediaVO::fromPetCyclopedia).collect(Collectors.toList());
        log.info("执行成功[查询宠物科普列表]");
        return petCyclopediaVOS;
    }

    @Override
    public PetCyclopediaVO getPetCyclopedia(Integer id) {
        PetCyclopedia petCyclopedia = petCyclopediaMapper.selectById(id);
        PetCyclopedia pet = new PetCyclopedia();
        pet.setId(petCyclopedia.getId());
        // 浏览量+1
        pet.setView(petCyclopedia.getView() + 1);
        petCyclopediaMapper.updateById(pet);
        log.info("执行成功[查询宠物科普]");
        return PetCyclopediaVO.fromPetCyclopedia(petCyclopedia);
    }
}
