package com.q7g.nucoj_spring.vo;

import com.q7g.nucoj_spring.enums.UserRoleEnum;
import com.q7g.nucoj_spring.po.UserContactInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

/**
 * 用户账号信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "用户账号")
public class UserVO {
    /**
     * 用户邮箱
     */
    @ApiModelProperty(name = "email", value = "用户邮箱", required = true, dataType = "String")
    private String email;

    /**
     * 用户密码
     */
    @ApiModelProperty(name = "password", value = "用户密码", required = true, dataType = "String")
    private String password;

    /**
     * 验证码
     */
    @ApiModelProperty(name = "code", value = "邮箱验证码", dataType = "String")
    private String code;

    private String id;

    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 用户姓名
     */
    private String name;
    /**
     * 用户学号
     */
    private String number;
    /**
     * 用户学院
     */
    private String college;
    /**
     * 用户联系方式
     */
    private UserContactInfoVO contact;
}
