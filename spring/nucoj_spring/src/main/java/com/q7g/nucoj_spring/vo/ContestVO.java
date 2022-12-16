package com.q7g.nucoj_spring.vo;

import com.q7g.nucoj_spring.po.Problem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContestVO {
    /**
     * 比赛ID
     */
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
    private List<String> entryUsers;
    /**
     * 比赛题目集
     */
    private List<ProblemVO> problems;
}
