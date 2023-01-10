package com.q7g.nucoj_spring.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.q7g.nucoj_spring.dto.JudgeStatusDTO;
import com.q7g.nucoj_spring.dto.PageDTO;
import com.q7g.nucoj_spring.dto.ProblemDTO;
import com.q7g.nucoj_spring.dto.ProblemExampleDTO;
import com.q7g.nucoj_spring.enums.JudgeModel;
import com.q7g.nucoj_spring.exceotion.BizException;
import com.q7g.nucoj_spring.po.Problem;
import com.q7g.nucoj_spring.po.ProblemExample;
import com.q7g.nucoj_spring.repository.ProblemRepository;
import com.q7g.nucoj_spring.service.JudgeService;
import com.q7g.nucoj_spring.service.ProblemService;
import com.q7g.nucoj_spring.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProblemServiceImpl implements ProblemService {
    @Autowired
    private ProblemRepository problemRepository;

    @Autowired
    private JudgeService judgeService;
    @Autowired
    private TagService tagService;

    @Override
    public List<ProblemDTO> getRecentProblem(int size) {
        // 获取时间降序排列的题目列表
        List<Problem> list = problemRepository.findAll(Sort.by(Sort.Direction.DESC, "time"));
        // 转换
        List<ProblemDTO> res = new LinkedList<>();
        for (Problem i : list) {
            if (res.size() >= size) break;
            // 更改数据类型
            res.add(ProblemDTO.of(i, 0));
        }
        return res;
    }

    @Override
    public ProblemDTO getProblem(String problemId, boolean admin) {
        Optional<Problem> byId = problemRepository.findById(problemId);
        if (!byId.isPresent()) throw new BizException("到达知识的边界");
        return ProblemDTO.of(byId.get(), admin ? 2 : 1);
    }

    /**
     * 如果没有选择当前的限制选项， 则将所有选项加入条件
     */
    private void init(List<String> tags) {
        if (tags.stream().noneMatch(item -> item.contains("级"))) {
            for (int i = 1; i <= 13; i ++ ) {
                String s = i + "级";
                tags.add(s);
            }
        }
        List<String> serviceTags = tagService.getTags();
        if (tags.stream().noneMatch(serviceTags::contains)) {
            tags.addAll(serviceTags);
        }
    }

    @Override
    public PageDTO<ProblemDTO> getProblems(int current, int size, String keywords, List<String> tags, boolean admin) {
        init(tags);
        // 分页器
        Pageable pageable = PageRequest.of(current - 1, size);
        // 转换
        Page<Problem> all = problemRepository.findAllByContextOrAuthorOrTitle(keywords, tags, pageable);
        return (PageDTO<ProblemDTO>) PageDTO.of(
                all.getTotalElements(),
                all.stream().map(item -> ProblemDTO.of(item, admin ? 2 : 0)).collect(Collectors.toList())
        );
    }

    @Override
    public void deleteProblem(String problemId) {
        problemRepository.deleteById(problemId);
    }

    @Override
    public void updateProblem(Problem problem) {
        if (problem.getId().equals("")) {
            long id = problemRepository.count() + 1;
            while (problemRepository.findById(String.valueOf(id)).isPresent()) id ++;
            problem.setId(String.valueOf(id));
            problemRepository.insert(problem);
        }
        else problemRepository.save(problem);
    }

    @Override
    public JudgeStatusDTO judge(String language, String problemId, String userId, String code) {
        Problem problem = problemRepository.findById(problemId).get();
        return judgeService.judge(problem, language, code, JudgeModel.ACM);
    }
}
