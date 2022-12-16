package com.q7g.nucoj_spring.service.impl;

import com.q7g.nucoj_spring.dto.PageDTO;
import com.q7g.nucoj_spring.dto.ProblemDTO;
import com.q7g.nucoj_spring.dto.TrainDTO;
import com.q7g.nucoj_spring.exceotion.BizException;
import com.q7g.nucoj_spring.po.Train;
import com.q7g.nucoj_spring.repository.TrainRepository;
import com.q7g.nucoj_spring.service.ProblemService;
import com.q7g.nucoj_spring.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrainServiceImpl implements TrainService {
    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private ProblemService problemService;

    /**
     * 转换题单对象
     * @param train
     * @param isAll 是否包含题目
     * @return
     */
    private TrainDTO getTrainDTO(Train train, boolean isAll) {
        TrainDTO trainDTO = TrainDTO.builder()
                .id(train.getId())
                .title(train.getTitle())
                .author(train.getAuthor())
                .build();
        if (isAll) {
            trainDTO.setProblems(train.getProblems().stream().map(item -> problemService.getProblem(item, false)).collect(Collectors.toList()));
        }
        return trainDTO;
    }

    @Override
    public PageDTO<TrainDTO> getTrains(int current, int size, String keywords) {
        // 分页器
        Pageable pageable = PageRequest.of(current - 1, size);
        // 创建样例
        Train train = Train.builder().title(keywords).build();
        ExampleMatcher matcher = ExampleMatcher.matching()//构建对象
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)//改变默认字符串匹配方式：模糊查询
                .withIgnoreCase(true);//改变默认大小写忽略方式：忽略大小写
        Example<Train> example = Example.of(train, matcher);
        // 查询
        long total = trainRepository.count(example);
        List<Train> list = trainRepository.findAll(example, pageable).toList();
        // 转换为数据传输对象
        List<TrainDTO> res = new LinkedList<>();
        for (Train i : list) {
            res.add(getTrainDTO(i, false));
        }
        return new PageDTO<>(total, res);
    }

    @Override
    public TrainDTO getTrain(String trainId) {
        Optional<Train> byId = trainRepository.findById(trainId);
        if (!byId.isPresent()) {
            throw new BizException("到达知识的边界");
        }
        return getTrainDTO(byId.get(), true);
    }
}
