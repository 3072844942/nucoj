package com.q7g.nucoj_spring.service;

import com.q7g.nucoj_spring.dto.PageDTO;
import com.q7g.nucoj_spring.dto.LogDTO;
import com.q7g.nucoj_spring.po.Log;

/**
 * 日志服务
 */
public interface LogService {
    /**
     * 获取链接集
     * @param current 页码
     * @param size 条数
     */
    PageDTO<LogDTO> getLogs(Integer current, Integer size);

    /**
     * 删除Log
     * @param id id
     */
    void deleteLog(String id);

    /**
     * 插入Log
     * @param log log
     */
    void insertLog(Log log);

    /**
     * 插入Log
     * @param optUrl 操作地址
     * @param requestMethod 情求方式
     * @param requestParam 请求参数
     * @param responseData  返回数据
     * @param user 操作用户
     */
    void insertLog(String optUrl, String requestMethod, String requestParam, String responseData, String user);
}
