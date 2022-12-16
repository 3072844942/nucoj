package com.q7g.nucoj_spring.service;

import com.q7g.nucoj_spring.dto.PageDTO;
import com.q7g.nucoj_spring.dto.TrainDTO;

/**
 * 题单服务
 */
public interface TrainService {
    /**
     * 获取符合条件的题单
     * @param current 页码
     * @param size 条数
     * @param keywords 搜索内容
     * @return 题单集
     */
    PageDTO<TrainDTO> getTrains(int current, int size, String keywords);

    /**
     * 获取题单
     * @param trainId 题单ID
     * @return 题单信息
     */
    TrainDTO getTrain(String trainId);
}
