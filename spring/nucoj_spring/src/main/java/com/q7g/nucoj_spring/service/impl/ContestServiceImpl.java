package com.q7g.nucoj_spring.service.impl;

import com.q7g.nucoj_spring.dto.*;
import com.q7g.nucoj_spring.enums.JudgeModel;
import com.q7g.nucoj_spring.exceotion.BizException;
import com.q7g.nucoj_spring.po.*;
import com.q7g.nucoj_spring.repository.ContestRepository;
import com.q7g.nucoj_spring.service.ContestService;
import com.q7g.nucoj_spring.service.JudgeService;
import com.q7g.nucoj_spring.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class ContestServiceImpl implements ContestService {

    @Autowired
    private ContestRepository contestRepository;

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private JudgeService judgeService;

    @Override
    public List<ContestDTO> getRecentContest(int size) {
        // 以时间降序获得集合
        List<Contest> all = contestRepository.findAll(Sort.by(Sort.Direction.DESC, "time"));
        return all.stream().limit(size).map(item -> ContestDTO.of(item, 0, "")).collect(Collectors.toList());
    }

    @Override
    public PageDTO<ContestDTO> getContests(int current, int size, String keywords) {
        // 分页器
        Pageable pageable = PageRequest.of(current - 1, size);
        // 查询
        Page<Contest> list = contestRepository.findAllByContextOrAuthorOrTitle(keywords, pageable);
        // 转换为数据传输对象
        return (PageDTO<ContestDTO>) PageDTO.of(
                list.getTotalElements(),
                list.stream().map(item -> ContestDTO.of(item, 0, "")).collect(Collectors.toList())
        );
    }

    @Override
    public ContestDTO getContest(String contestId, boolean admin, String userId) {
        Optional<Contest> byId = contestRepository.findById(contestId);
        // 如果不存在
        if (!byId.isPresent()) {
            throw new BizException("到达知识的边界");
        }
        Contest contest = byId.get();
        if (admin) {
            return ContestDTO.of(contest, 2, userId);
        } else if (contest.getStartTime() <= System.currentTimeMillis())
            return ContestDTO.of(contest, 1, userId);
        else return ContestDTO.of(contest, 0, userId);
    }

    @Override
    public void deleteContest(String contestId) {
        contestRepository.deleteById(contestId);
    }

    @Override
    public ProblemDTO getAdminContestProblem(String contestId, String problemId) {
        Contest contest = contestRepository.findById(contestId).get();
        List<Problem> collect = contest.getProblems().stream().filter(item -> item.getId().equals(problemId)).collect(Collectors.toList());
        return ProblemDTO.of(collect.get(0), 2);
    }

    @Override
    public void updateContestProblem(String contestId, Problem problem) {
        Contest contest = contestRepository.findById(contestId).get();
        // todo 是否可以后台传入一个ID
        if (Objects.isNull(problem.getId()) || problem.getId().trim().equals("")) {
            problem.setId(problem.getTitle());
            contest.getProblems().add(problem);
        } else {
            for (int i = 0; i < contest.getProblems().size(); i++)
                if (contest.getProblems().get(i).getId().equals(problem.getId()))
                    contest.getProblems().set(i, problem);
        }
        contestRepository.save(contest);
    }

    private void insertContest(Contest contest) {
        long id = contestRepository.count() + 1;
        while (contestRepository.findById(String.valueOf(id)).isPresent()) id++;
        contest.setId(String.valueOf(id));
        contest.setEntryUsers(new LinkedList<>());
        contestRepository.insert(contest);
    }

    @Override
    public void updateContest(Contest contest) {
        Optional<Contest> byId = contestRepository.findById(contest.getId());
        if (!byId.isPresent()) insertContest(contest);
        else {
            contest.setEntryUsers(byId.get().getEntryUsers());
            contestRepository.save(contest);
        }
    }

    @Override
    public void deleteContestProblem(String contestId, String problemId) {
        Contest contest = contestRepository.findById(contestId).get();
        contest.getProblems().removeIf(item -> item.getId().equals(problemId));
        updateContest(contest);
    }

    @Override
    public PageDTO<UserDetailDTO> getContestUser(String contestId, int current, int size) {
        Contest contest = contestRepository.findById(contestId).get();
        List<UserDetailDTO> list = new LinkedList<>();
        for (int i = (current - 1) * size; i < current * size && i < contest.getEntryUsers().size(); i++)
            list.add(new UserDetailDTO(userInfoService.getUser(contest.getEntryUsers().get(i))));
        return new PageDTO<>(contest.getEntryUsers().size(), list);
    }

    @Override
    public ProblemDTO getContestProblem(String contestId, String problemId) {
        Contest contest = contestRepository.findById(contestId).get();
        List<Problem> collect = contest.getProblems().stream().filter(item -> item.getId().equals(problemId)).collect(Collectors.toList());
        return ProblemDTO.of(collect.get(0), 1);
    }

    @Override
    public JudgeStatusDTO judge(String contestId, String language, String problemId, String userId, String code) {
        Contest contest = contestRepository.findById(contestId).get();
        List<Problem> collect = contest.getProblems().stream().filter(item -> item.getId().equals(problemId)).collect(Collectors.toList());
        Problem problem = collect.get(0);
        JudgeStatusDTO judge = judgeService.judge(problem, language, code, JudgeModel.ACM);

        // 写入提交记录
        // 只看最后一个节点的记录
        int condition = judge.getJudgeResults().get(judge.getJudgeResults().size() - 1).getCondition();
        Submit submit = Submit.builder()
                .id(String.valueOf(contest.getSubmits().size() + 1))
                .time(judge.getJudgeEndTime())
                .condition(condition)
                .language(language)
                .problemId(problemId)
                .code(code)
                .userId(userId)
                .build();
        contest.getSubmits().add(submit);

        if (condition == 16) { // ce 不计入榜单
            return judge;
        }

        // 写入排名
        Map<String, List<ProblemState>> rank = contest.getRank();
        if (!rank.containsKey(userId)) { // 如果没有当前用户的记录
            // 放入一个空的记录
            List<ProblemState> list = new LinkedList<>();
            contest.getProblems().forEach(item ->
                    list.add(ProblemState.builder().problemId(item.getId())
                            .wrong(0).accept(false).punish(0L).build()));
            rank.put(userId, list);
        }
        // 找到当前题目的对应记录
        ProblemState problemState = rank.get(userId).stream().filter(item -> item.getProblemId().equals(problemId))
                .collect(Collectors.toList()).get(0);
        if (!problemState.getAccept()) { // 当没有通过当前题目时
            // 修改当前题目通过率
            String rate = problem.getRate();
            if (Objects.isNull(rate)) rate = "0/0";
            int a = Integer.parseInt(rate.split("/")[0]);
            int b = Integer.parseInt(rate.split("/")[1]) + 1;

            if (condition == 0) { // 如果通过了
                a++; // 只有通过时才加入
                problemState.setAccept(true);
                // 罚时为 前面的罚时 + 当前时间
                problemState.setPunish(problemState.getPunish() + System.currentTimeMillis() - contest.getStartTime());
            } else { // 没有通过
                // 罚时为 前面罚时 + 20分钟罚时
                problemState.setPunish(problemState.getPunish() + 20 * 60 * 1000);
            }
            problemState.setWrong(problemState.getWrong() + 1);
            // 修改通过率
            problem.setRate(a + "/" + b);
        }// 通过后不记录

        // 更新榜单
        updateContest(contest);
        return judge;
    }

    private ContestRankUserDTO getUserContestRankUserDTO(Contest contest, String userId) {
        if (!contest.getRank().containsKey(userId)) return null;
        List<ProblemState> list = contest.getRank().get(userId);
        User user = userInfoService.getUser(userId);
        AtomicInteger index = new AtomicInteger(0);
        return ContestRankUserDTO.builder()
                .name(user.getName().equals("") ? user.getNickName() : user.getName())
                .userId(userId)
                .number(user.getNumber())
                .states(list.stream().map(item -> {
                            ProblemState problemState = list.get(index.getAndAdd(1));
                            return ContestRankUserProblemDTO.builder()
                                    .accept(problemState.getAccept())
                                    .punish(problemState.getPunish())
                                    .wrong(problemState.getWrong())
                                    .build();
                        }).collect(Collectors.toList())
                )
                .build();
    }

    @Override
    public ContestRankDTO getContestRank(String contestId, int current, int size, String userId) {
        Contest contest = contestRepository.findById(contestId).get();
        List<ContestColumnDTO> columns = contest.getProblems().stream()
                .map(item -> ContestColumnDTO.builder().problemId(item.getId()).rate(item.getRate()).build())
                .collect(Collectors.toList());

        List<ContestRankUserDTO> data = new LinkedList<>();
        // 以key值获取用户的排名状态
        contest.getRank().keySet().stream().skip((long) (current - 1) * size).limit(size).forEach(item -> data.add(getUserContestRankUserDTO(contest, item)));
        // 排序
        Collections.sort(data);

        // 合并
        List<ContestRankUserDTO> mergeData = data;

        // 和前台通讯存在问题， 先不显示自己
//        List<ContestRankUserDTO> mergeData;
//        if (Objects.isNull(userId) || userId.equals("")) mergeData = data;
//        else {
//            // 如果用户是登录状态， 则将用户的放到第一位
//            mergeData = new LinkedList<>(Collections.singletonList(getUserContestRankUserDTO(contest, userId)));
//            mergeData.addAll(data);
//        }

        return ContestRankDTO.builder()
                .columns(columns)
                .data(mergeData)
                .total((long) contest.getRank().size())
                .build();
    }

    @Override
    public ContestRankUserDTO getUserContestState(String userId, String contestId) {
        Contest contest = contestRepository.findById(contestId).get();
        return getUserContestRankUserDTO(contest, userId);
    }

    @Override
    public PageDTO<ContestSubmitDTO> getContestSubmits(String contestId, int current, int size) {
        Contest contest = contestRepository.findById(contestId).get();
        List<Submit> submits = contest.getSubmits();
        Collections.reverse(submits);
        List<ContestSubmitDTO> collect = submits.stream().skip((current - 1) * size).limit(size)
                .map(item -> {
                    User user = userInfoService.getUser(item.getUserId());
                    return ContestSubmitDTO.builder()
                            .id(item.getId())
                            .problemId(item.getProblemId())
                            .user(user.getName().equals("") ? user.getNickName() : user.getName())
                            .condition(item.getCondition())
                            .language(item.getLanguage())
                            .code(item.getCode())
                            .time(item.getTime())
                            .build();
                })
                .collect(Collectors.toList());
        return (PageDTO<ContestSubmitDTO>) PageDTO.of(submits.size(), collect);
    }
}
