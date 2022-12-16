package com.q7g.nucoj_spring.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.parameters.P;

import java.util.List;

/**
 * 集合返回值
 * @param <T> 类型
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageDTO<T> {
    /**
     * 总数
     */
    @ApiModelProperty(name = "total", value = "总数",  dataType = "long")
    long total;
    /**
     * 列表
     */
    @ApiModelProperty(name = "list", value = "列表",  dataType = "array")
    List<T> list;

    public static PageDTO<?> of(long total, List<?> list) {
        return new PageDTO<>(total, list);
    }
}
