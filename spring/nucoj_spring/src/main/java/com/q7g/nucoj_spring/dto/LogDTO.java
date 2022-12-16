package com.q7g.nucoj_spring.dto;

import com.q7g.nucoj_spring.po.Log;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogDTO {
    private String id;
    /**
     * 操作路径
     */
    @ApiModelProperty(name = "optUrl", value = "操作路径",  dataType = "string")
    private String optUrl;
    /**
     * 请求方式
     */
    @ApiModelProperty(name = "requestMethod", value = "请求方式",  dataType = "string")
    private String requestMethod;
    /**
     * 请求参数
     */
    @ApiModelProperty(name = "requestParam", value = "请求参数",  dataType = "string")
    private String requestParam;
    /**
     * 返回数据
     */
    @ApiModelProperty(name = "responseData", value = "返回数据",  dataType = "responseData")
    private String responseData;
    /**
     * 用户昵称/姓名
     */
    @ApiModelProperty(name = "user", value = "请求用户",  dataType = "string")
    private String user;
    /**
     * 创建时间
     */
    @ApiModelProperty(name = "createTime", value = "请求时间",  dataType = "long")
    private Long createTime;

    public static LogDTO of(Log log) {
        return LogDTO.builder()
                .id(log.getId())
                .optUrl(log.getOptUrl())
                .requestMethod(log.getRequestMethod())
                .requestParam(log.getRequestParam())
                .responseData(log.getResponseData())
                .user(log.getUser())
                .createTime(log.getCreateTime())
                .build();
    }
}
