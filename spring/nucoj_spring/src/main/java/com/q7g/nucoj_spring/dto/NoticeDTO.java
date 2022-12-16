package com.q7g.nucoj_spring.dto;

import com.q7g.nucoj_spring.po.Notice;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 公告信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("公告信息")
public class NoticeDTO {
    /**
     * 公告ID
     */
    @ApiModelProperty(name = "id", value = "公告ID", dataType = "string")
    private String id;
    /**
     * 公告标题
     */
    @ApiModelProperty(name = "title", value = "公告标题", dataType = "string")
    private String title;
    /**
     * 公告创建时间
     */
    @ApiModelProperty(name = "title", value = "公告创建时间", dataType = "long")
    private Long time;
    /**
     * 公告创建人
     */
    @ApiModelProperty(name = "author", value = "公告创建人", dataType = "string")
    private String author;
    /**
     * 公告内容
     */
    @ApiModelProperty(name = "context", value = "公告正文", dataType = "string")
    private String context;

    public static NoticeDTO of(Notice notice, boolean isAll) {
        return NoticeDTO.builder()
                .id(notice.getId())
                .time(notice.getTime())
                .title(notice.getTitle())
                .author(notice.getAuthor())
                .context(isAll ? notice.getContext() : null)
                .build();
    }
}
