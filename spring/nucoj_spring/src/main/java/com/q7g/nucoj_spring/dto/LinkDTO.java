package com.q7g.nucoj_spring.dto;

import com.q7g.nucoj_spring.po.Link;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 链接信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("友情链接信息")
public class LinkDTO {
    /**
     * 链接标题
     */
    @ApiModelProperty(name = "title", value = "链接标题", dataType = "string")
    private String title;
    /**
     * 链接地址
     */
    @ApiModelProperty(name = "link", value = "链接标题", dataType = "string")
    private String link;

    public static LinkDTO of(Link link) {
        return LinkDTO.builder()
                .link(link.getLink())
                .title(link.getTitle())
                .build();
    }
}
