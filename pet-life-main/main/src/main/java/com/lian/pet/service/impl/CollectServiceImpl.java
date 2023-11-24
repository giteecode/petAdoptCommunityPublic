package com.lian.pet.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lian.pet.common.basic.enums.DataTypeEnum;
import com.lian.pet.domain.dto.AddCollectDTO;
import com.lian.pet.domain.dto.QueryAdoptsInDTO;
import com.lian.pet.domain.dto.QueryCollectDTO;
import com.lian.pet.domain.entity.Collect;
import com.lian.pet.domain.vo.PetAdoptVO;
import com.lian.pet.mapper.CollectMapper;
import com.lian.pet.service.CollectService;
import com.lian.pet.service.PetAdoptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Desc:

 * @Time: 2022/2/18 13:55
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CollectServiceImpl implements CollectService {
    private final CollectMapper collectMapper;
    private final PetAdoptService petAdoptService;

    @Override
    public Boolean addCollect(AddCollectDTO req) {
        Collect collect = new Collect();
        collect.setUserid(req.getUserId()).setFromId(req.getFromId()).setType(req.getType());
        if (!isCollect(req)) {
            log.error("已添加过收藏");
            return false;
        }
        collectMapper.insert(collect);
        log.info("执行成功[添加收藏]");
        return true;
    }

    @Override
    public Boolean removeCollect(AddCollectDTO req) {
        collectMapper.delete(Wrappers.<Collect>lambdaQuery()
                .eq(Collect::getUserid, req.getUserId())
                .eq(Collect::getFromId, req.getFromId())
                .eq(Collect::getType, req.getType()));
        return true;
    }

    @Override
    public Boolean isCollect(AddCollectDTO req) {
        List<Collect> collects = collectMapper.selectList(Wrappers.<Collect>lambdaQuery()
                .eq(Collect::getUserid, req.getUserId())
                .eq(Collect::getFromId, req.getFromId())
                .eq(Collect::getType, req.getType()));
        if (collects.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public <T> List<T> queryCollect(QueryCollectDTO req) {
        List<Collect> collects = collectMapper.selectList(Wrappers.<Collect>lambdaQuery()
                .eq(Collect::getUserid, req.getUserId()));
        List<Integer> ids = collects.stream().map(Collect::getFromId).collect(Collectors.toList());
        // 目前只有宠物领养 有收藏功能
        if (req.getType().equals(DataTypeEnum.ADOPT.name())) {
            List<PetAdoptVO> petAdoptVOS = petAdoptService.queryAdoptsInIds(QueryAdoptsInDTO.builder()
                    .ids(ids)
                    .pageNum(req.pageNum)
                    .pageSize(req.getPageSize())
                    .build());
            return (List<T>) petAdoptVOS;
        }
        return null;
    }
}
