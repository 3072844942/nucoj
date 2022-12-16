package com.q7g.nucoj_spring.service.impl;

import com.q7g.nucoj_spring.dto.PageDTO;
import com.q7g.nucoj_spring.dto.SolutionDTO;
import com.q7g.nucoj_spring.exceotion.BizException;
import com.q7g.nucoj_spring.po.Solution;
import com.q7g.nucoj_spring.repository.SolutionRepository;
import com.q7g.nucoj_spring.repository.UserRepository;
import com.q7g.nucoj_spring.service.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SolutionServiceImpl implements SolutionService {
    @Autowired
    private SolutionRepository solutionRepository;
    @Override
    public PageDTO<SolutionDTO> getSolutions(int current, int size, String keywords, String userId) {
        // 分页器
        Pageable pageable = PageRequest.of(current - 1, size);
        // 查询
        Page<Solution> all = solutionRepository.findAllByContextOrAuthorOrTitle(keywords, pageable);
        return (PageDTO<SolutionDTO>) PageDTO.of(
                all.getTotalElements(),
                all.stream().map(item -> SolutionDTO.of(item, false)).collect(Collectors.toList())
        );
    }

    @Override
    public SolutionDTO getSolution(String solutionId) {
        Optional<Solution> byId = solutionRepository.findById(solutionId);
        if (!byId.isPresent()){
            throw new BizException("到达知识的边界");
        }
        return SolutionDTO.of(byId.get(), true);
    }

    private void insertSolution(String title, Long time, String context, String author) {
        // 找到不存在的Id
        long count = solutionRepository.count() + 1;
        while (solutionRepository.findById(String.valueOf(count)).isPresent())
            count++;
        String id = String.valueOf(count);
        Solution solution = Solution.builder()
                .id(id)
                .title(title)
                .time(time)
                .context(context)
                .author(author)
                .build();
        solutionRepository.insert(solution);
    }

    @Override
    public void updateSolution(String id, String title, Long time, String context, String author) {
        // 如果存在当前题解
        if (Objects.isNull(id)) insertSolution(title, time, context, author);
        else {
            Solution solution = Solution.builder()
                    .id(id)
                    .title(title)
                    .time(time)
                    .context(context)
                    .author(author)
                    .build();
            solutionRepository.save(solution);
        }
    }

    @Override
    public void deleteSolution(String solutionId) {
        solutionRepository.deleteById(solutionId);
    }
}
