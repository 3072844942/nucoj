package com.q7g.nucoj_spring.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.util.LinkedList;
import java.util.List;

/**
 * 查询条件
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "查询条件")
public class ConditionVO {
    /**
     * 页码
     */
    @ApiModelProperty(name = "current", value = "页码", dataType = "integer")
    private Integer current;
    /**
     * 条数
     */
    @ApiModelProperty(name = "size", value = "条数", dataType = "integer")
    private Integer size;
    /**
     * 搜索内容
     */
    @Builder.Default
    @ApiModelProperty(name = "keywords", value = "内容", dataType = "string")
    private String keywords = "";
    /**
     * 标签要求
     */
    @Builder.Default
    @ApiModelProperty(name = "tags", value = "标签", dataType = "array")
    private List<String> tags = new LinkedList<>();

    @Builder.Default
    private String id = "";

    private Integer role;

    private List<Object> list;

    private String contestId;

    private String userId;
}
