package com.lian.pet.domain.vo;

import com.lian.pet.domain.entity.AnswerPrize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Desc:

 * @Time: 2022/2/17 14:42
 */
@Data
@Builder
@ApiModel("查询题目")
public class AnswerPrizeVO {
    private Integer id;
    @ApiModelProperty(value = "题目")
    private String subject;
    @ApiModelProperty(value = "选项[数组]")
    private List<Option> options;
    @ApiModelProperty(value = "正确答案")
    private String answer;
    @ApiModelProperty(value = "类型")
    private String type;

    public static AnswerPrizeVO fromAnswerPrize(AnswerPrize entity) {
        return AnswerPrizeVO.builder()
                .id(entity.getId())
                .subject(entity.getSubject())
                .options(fromOption(entity.getOptions()))
                .answer(entity.getAnswer())
                .type(entity.getType())
                .build();
    }

    public static List<Option> fromOption(String options) {
        List<Option> optionList = new ArrayList<>();
        Arrays.asList(options.split(",")).forEach(item -> {
            optionList.add(Option.builder()
                    .option(item)
                    // 对应前端按钮type属性
                    .btnType("default")
                    .build());
        });
        return optionList;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Option {
        private String option;
        private String btnType;
    }
}
