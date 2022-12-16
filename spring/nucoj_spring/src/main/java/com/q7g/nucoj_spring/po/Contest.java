package com.q7g.nucoj_spring.po;

import com.q7g.nucoj_spring.vo.ContestVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 比赛
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("contest")
public class Contest {
    /**
     * 比赛ID
     */
    @Id
    private String id;
    /**
     * 比赛标题
     */
    private String title;
    /**
     * 比赛创建人
     */
    private String author;
    /**
     * 比赛开始时间
     */
    private Long startTime;
    /**
     * 比赛持续时间
     */
    private Long time;
    private String context;
    /**
     * 比赛题目集
     */
    private List<Problem> problems;
    /**
     * 加入的用户集合
     */
    private List<String> entryUsers;

    private List<Submit> submits;

    private Map<String, List<ProblemState>> rank;

    public static Contest of(ContestVO contestVO) {
        return Contest.builder()
                .id(contestVO.getId())
                .title(contestVO.getTitle())
                .startTime(contestVO.getStartTime())
                .time(contestVO.getTime())
                .context(contestVO.getContext())
                .author(contestVO.getAuthor())
                .problems(contestVO.getProblems().stream().map(Problem::of).collect(Collectors.toList()))
                .submits(new LinkedList<>())
                .rank(new HashMap<>())
                .build();
    }
}
