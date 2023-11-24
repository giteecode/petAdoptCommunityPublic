package com.lian.pet.domain.dto;

import com.lian.pet.domain.page.BasePageQueryReq;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Desc:

 * @Time: 2022/2/17 17:42
 */
@Data
@ApiModel("查询答题记录")
public class QueryAnswerRecordsDTO extends BasePageQueryReq {
    private String userId;
}
