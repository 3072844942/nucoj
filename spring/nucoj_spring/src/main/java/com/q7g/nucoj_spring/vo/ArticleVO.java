package com.q7g.nucoj_spring.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 公告 / 题解 / 分享
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "公告 / 题解 / 分享")
public class ArticleVO {
    @ApiModelProperty(name = "id", value = "ID", dataType = "string")
    private String id;
    @ApiModelProperty(name = "title", value = "标题", dataType = "string")
    private String title;
    @ApiModelProperty(name = "time", value = "创建时间", dataType = "long")
    private Long time;
    @ApiModelProperty(name = "context", value = "正文", dataType = "string")
    private String context;
    @ApiModelProperty(name = "author", value = "创建人", dataType = "string")
    private String author;
}
