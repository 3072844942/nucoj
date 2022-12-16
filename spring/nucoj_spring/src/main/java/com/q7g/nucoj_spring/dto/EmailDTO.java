package com.q7g.nucoj_spring.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 邮件
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailDTO {
    /**
     * 邮箱号
     */
    @ApiModelProperty(name = "email", value = "收件人", dataType = "string")
    private String email;

    /**
     * 主题
     */
    @ApiModelProperty(name = "subject", value = "邮件主题", dataType = "string")
    private String subject;

    /**
     * 内容
     */
    @ApiModelProperty(name = "content", value = "邮件内容", dataType = "string")
    private String content;

}
