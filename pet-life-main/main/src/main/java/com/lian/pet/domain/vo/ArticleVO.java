package com.lian.pet.domain.vo;

import com.lian.pet.domain.entity.Article;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @Desc:

 * @Time: 2022/2/3 16:15
 */
@Data
@Builder
@ApiModel("查询文章")
public class ArticleVO {
    private Integer id;
    @ApiModelProperty(value = "标签")
    private String label;
    @ApiModelProperty(value = "文章标题")
    private String title;
    @ApiModelProperty(value = "文章链接(H5)")
    private String url;
    @ApiModelProperty(value = "背景色")
    private String bgColor;
    @ApiModelProperty(value = "封面图")
    private String picture;
    @ApiModelProperty(value = "浏览量")
    private Integer view;

    public static ArticleVO fromArticle(Article entity) {
        return ArticleVO.builder()
                .id(entity.getId())
                .label(entity.getLabel())
                .title(entity.getTitle())
                .url(entity.getUrl())
                .bgColor(entity.getBgColor())
                .picture(entity.getPicture())
                .view(entity.getView())
                .build();
    }
}
