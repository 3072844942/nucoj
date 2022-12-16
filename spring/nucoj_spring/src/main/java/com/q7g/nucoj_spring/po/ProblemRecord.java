package com.q7g.nucoj_spring.po;

import com.q7g.nucoj_spring.enums.ProblemStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

/**
 * TODO 提交信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProblemRecord {
    @Id
    private String userId;
    private String problemId;
    private String code;
    private ProblemStatusEnum status;
    private Integer timeCost;
    private Integer memoryCost;
    private Long time;
}
