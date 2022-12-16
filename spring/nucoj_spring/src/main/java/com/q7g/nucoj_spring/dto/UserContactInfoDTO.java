package com.q7g.nucoj_spring.dto;

import com.q7g.nucoj_spring.po.UserContactInfo;
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
public class UserContactInfoDTO {
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

    public UserContactInfoDTO(UserContactInfo userContactInfo) {
        this.qq = userContactInfo.getQq();
        this.github = userContactInfo.getGithub();
        this.blog = userContactInfo.getBlog();
    }
}
