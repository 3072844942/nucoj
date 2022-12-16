package com.q7g.judgehost.vo;

import com.q7g.judgehost.dto.SingleJudgeResultDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 对某次判题最终结果的表现层对象
 *
 * @author yuzhanglong
 * @date 2020-6-29 22:41:12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JudgeConditionVO {
    private List<SingleJudgeResultDTO> judgeResults;
    private String submissionId;
    private Long judgeEndTime;
    private List<String> extraInfo;


    public List<SingleJudgeResultDTO> getJudgeResults() {
        return judgeResults;
    }

    public Long getJudgeEndTime() {
        return judgeEndTime;
    }


    public JudgeConditionVO(List<SingleJudgeResultDTO> judgeResults, List<String> compileResult, String submissionId) {
        this.judgeResults = judgeResults;
        this.submissionId = submissionId;
        this.extraInfo = compileResult;
        this.judgeEndTime = System.currentTimeMillis();
    }

    public List<String> getExtraInfo() {
        return extraInfo;
    }

    public String getSubmissionId() {
        return submissionId;
    }

    @Override
    public String toString() {
        return "JudgeConditionVO{" +
                "judgeResults=" + judgeResults +
                ", judgeEndTime='" + judgeEndTime + '\'' +
                '}';
    }
}
