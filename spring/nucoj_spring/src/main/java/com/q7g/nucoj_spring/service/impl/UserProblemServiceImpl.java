package com.q7g.nucoj_spring.service.impl;

import com.q7g.nucoj_spring.enums.ProblemStatusEnum;
import com.q7g.nucoj_spring.exceotion.BizException;
import com.q7g.nucoj_spring.po.User;
import com.q7g.nucoj_spring.repository.UserRepository;
import com.q7g.nucoj_spring.service.UserProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserProblemServiceImpl implements UserProblemService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<String> getUserAcceptProblem(String userId) {
        Optional<User> byId = userRepository.findByUsername(userId);
        if (!byId.isPresent()) throw new BizException("发生系统错误");
        return byId.get().getAccept_problem();
    }

    @Override
    public List<String> getUserTryProblem(String userId) {
        Optional<User> byId = userRepository.findByUsername(userId);
        if (!byId.isPresent()) throw new BizException("发生系统错误");
        return byId.get().getTry_problem();
    }

    @Override
    public ProblemStatusEnum getUserProblemStatus(String userId, String problemId) {
        List<String> accept = getUserTryProblem(userId);
        if (accept.contains(problemId)) return ProblemStatusEnum.ACCEPT;
        List<String> tryProblem = getUserTryProblem(userId);
        if (tryProblem.contains(problemId)) return ProblemStatusEnum.WRONG;
        return ProblemStatusEnum.NONE;
    }
}
