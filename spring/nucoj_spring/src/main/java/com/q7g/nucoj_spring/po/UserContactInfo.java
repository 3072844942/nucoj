package com.q7g.nucoj_spring.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户联系方式
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserContactInfo {
    /**
     * 用户QQ
     */
    @ApiModelProperty(name = "qq", value = "QQ", dataType = "string")
    private String qq;
    /**
     * 用户Github地址
     */
    @ApiModelProperty(name = "github", value = "Github", dataType = "string")
    private String github;
    /**
     * 用户博客地址
     */
    @ApiModelProperty(name = "blog", value = "Blog", dataType = "string")
    private String blog;
}
