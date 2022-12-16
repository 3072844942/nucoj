package com.q7g.nucoj_spring.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户实名信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户实名信息")
public class UserInfoDTO {
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
     * 用户毕业去向
     */
    @ApiModelProperty(name = "destination", value = "用户去向", dataType = "string")
    private String destination;
    /**
     * 用户联系方式
     */
    @ApiModelProperty(name = "contact", value = "用户联系方式", dataType = "string")
    private UserContactInfoDTO contact;
}
