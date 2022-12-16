package com.q7g.nucoj_spring.dto;

import com.q7g.nucoj_spring.po.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户基本信息")
public class UserBaseDTO {
    /**
     * 用户ID， 用email/username作为ID
     */
    @ApiModelProperty(name = "id", value = "用户ID", dataType = "string")
    private String id;
    /**
     * 用户昵称
     */
    @ApiModelProperty(name = "name", value = "用户昵称", dataType = "string")
    private String nickname;
    /**
     * 用户头像
     */
    @ApiModelProperty(name = "avatar", value = "用户头像", dataType = "string")
    private String avatar;
    /**
     * 用户等级
     */
    @ApiModelProperty(name = "grade", value = "用户等级", dataType = "string")
    private Integer grade;
    /**
     * 用户角色
     */
    @ApiModelProperty(name = "role", value = "用户角色", dataType = "string")
    private Integer role;
    /**
     * 身份认证
     */
    @ApiModelProperty(name = "token", value = "验证token", dataType = "string")
    private String token;

    public static UserBaseDTO of(User user) {
        return UserBaseDTO.builder()
                .id(user.getId())
                .nickname(user.getNickName())
                .role(user.getRole().getCode())
                .grade(user.getGrade())
                .avatar(user.getAvatar())
                .build();

    }
}
