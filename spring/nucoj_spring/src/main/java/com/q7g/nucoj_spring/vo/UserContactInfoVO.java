package com.q7g.nucoj_spring.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserContactInfoVO {
    /**
     * 用户QQ
     */
    private String qq;
    /**
     * 用户Github地址
     */
    private String github;
    /**
     * 用户博客地址
     */
    private String blog;
}
