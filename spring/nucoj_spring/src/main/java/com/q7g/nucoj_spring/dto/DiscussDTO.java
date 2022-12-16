package com.q7g.nucoj_spring.dto;

import com.q7g.nucoj_spring.po.Discuss;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分享信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("分享信息")
public class DiscussDTO {
    /**
     * 分享ID
     */
    @ApiModelProperty(name = "id", value = "分享ID", dataType = "string")
    private String id;
    /**
     * 分享标题
     */
    @ApiModelProperty(name = "title", value = "分享标题", dataType = "string")
    private String title;
    /**
     * 分享创建人
     */
    @ApiModelProperty(name = "author", value = "分享创建人", dataType = "string")
    private String author;
    /**
     * 分享创建时间
     */
    @ApiModelProperty(name = "time", value = "分享创建时间", dataType = "long")
    private Long time;
    /**
     * 分享内容
     */
    @ApiModelProperty(name = "context", value = "分享正文", dataType = "string")
    private String context;

    public static DiscussDTO of(Discuss discuss, boolean isAll) {
        return DiscussDTO.builder()
                .id(discuss.getId())
                .author(discuss.getAuthor())
                .title(discuss.getTitle())
                .time(discuss.getTime())
                .context(isAll ? discuss.getContext() : null)
                .build();
    }
}
