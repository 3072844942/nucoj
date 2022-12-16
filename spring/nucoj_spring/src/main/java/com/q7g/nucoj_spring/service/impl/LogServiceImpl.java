package com.q7g.nucoj_spring.service.impl;

import com.q7g.nucoj_spring.dto.PageDTO;
import com.q7g.nucoj_spring.dto.LogDTO;
import com.q7g.nucoj_spring.po.Log;
import com.q7g.nucoj_spring.repository.LogRepository;
import com.q7g.nucoj_spring.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogRepository logRepository;

    @Override
    public PageDTO<LogDTO> getLogs(Integer current, Integer size) {
        // 分页器
        Pageable pageable = PageRequest.of(current - 1, size);
        // 排序函数
        Page<Log> all = logRepository.findAll(pageable);
        return (PageDTO<LogDTO>) PageDTO.of(
                all.getTotalElements(),
                all.stream().map(LogDTO::of).collect(Collectors.toList())
        );
    }

    @Override
    public void deleteLog(String id) {
        logRepository.deleteById(id);
    }

    @Override
    public void insertLog(Log log) {
        logRepository.insert(log);
    }

    @Override
    public void insertLog(String optUrl, String requestMethod,String requestParam,String responseData, String user) {
        insertLog(Log.builder()
//                .id(System.currentTimeMillis() + "_" + optUrl)
                .optUrl(optUrl)
                .requestMethod(requestMethod)
                .requestParam(requestParam)
                .responseData(responseData)
                .user(user)
                .createTime(System.currentTimeMillis())
                .build());
    }
}
