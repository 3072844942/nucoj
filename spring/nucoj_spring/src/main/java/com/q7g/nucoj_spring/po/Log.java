package com.q7g.nucoj_spring.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * 操作日志
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("log")
public class Log {
    /**
     * 日志Id
     */
    @Id
    private String id;
    /**
     * 操作路径
     */
    private String optUrl;
    /**
     * 请求方式
     */
    private String requestMethod;
    /**
     * 请求参数
     */
    private String requestParam;
    /**
     * 返回数据
     */
    private String responseData;
    /**
     * 用户昵称/姓名
     */
    private String user;
    /**
     * 创建时间
     */
    private Long createTime;
}
