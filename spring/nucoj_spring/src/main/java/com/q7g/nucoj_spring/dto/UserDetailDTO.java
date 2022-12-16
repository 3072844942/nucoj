package com.q7g.nucoj_spring.dto;

import com.q7g.nucoj_spring.po.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户详细信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailDTO {
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
     * 用户姓名
     */
    @ApiModelProperty(name = "name", value = "用户姓名", dataType = "string")
    private String name;
    /**
     * 用户学号
     */
    @ApiModelProperty(name = "number", value = "用户学号", dataType = "string")
    private String number;
    /**
     * 用户学院
     */
    @ApiModelProperty(name = "college", value = "用户学院", dataType = "string")
    private String college;
    /**
     * 用户联系方式
     */
    @ApiModelProperty(name = "contact", value = "用户联系方式", dataType = "string")
    private UserContactInfoDTO contact;

    public UserDetailDTO(User user) {
        this.id = user.getUsername();
        this.nickname = user.getNickName();
        this.role = user.getRole().getCode();
        this.grade = user.getGrade();
        this.avatar = user.getAvatar();
        this.name = user.getName();
        this.number = user.getNumber();
        this.college = user.getCollege();
        this.contact = new UserContactInfoDTO(user.getContact());
    }
}
