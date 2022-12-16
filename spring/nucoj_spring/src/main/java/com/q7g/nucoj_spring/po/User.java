package com.q7g.nucoj_spring.po;

import com.q7g.nucoj_spring.enums.UserRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * 用户
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("user")
public class User {
    @Id
    private String id;
    /**
     * 用户账号
     */
    private String username;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 用户角色
     */
    private UserRoleEnum role;

    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 用户等级
     */
    private Integer grade;
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
    private UserContactInfo contact;

    /**
     * 用户通过题目
     */
    private List<String> accept_problem;
    /**
     * 用户尝试题目
     */
    private List<String> try_problem;
}
