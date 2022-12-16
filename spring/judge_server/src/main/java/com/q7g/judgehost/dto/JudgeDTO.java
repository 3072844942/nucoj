package com.q7g.judgehost.dto;

import com.q7g.judgehost.exception.http.NotFoundException;
import com.q7g.judgehost.utils.validator.LanguageTypeAccepted;
import com.q7g.judgehost.core.enumeration.JudgeConfigDefaultEnum;
import com.q7g.judgehost.core.enumeration.JudgePreferenceEnum;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 判题数据传输对象
 * 如果用户没有传入某些非必填的限制
 * (例如时间限制、内存限制)时，我们会将这些内容置换成默认的配置
 * 同时防止了NPE错误的发生。
 *
 * @see JudgeConfigDefaultEnum 默认配置的枚举类
 * @see JudgePreferenceEnum 判题偏好配置
 */

@LanguageTypeAccepted
public class JudgeDTO {
    @NotNull(message = "代码不得为空")
    private String submissionCode;

    @DecimalMax(value = "10000", message = "实际时间限制最大为10 * 1000ms")
    private Integer realTimeLimit;

    @DecimalMax(value = "10000", message = "cpu时间限制最大为10 * 1000ms")
    private Integer cpuTimeLimit;

    @DecimalMin(value = "3000", message = "内存限制最小为3000kb")
    @DecimalMax(value = "262144", message = "内存限制最大为256 * 1024kB")
    private Integer memoryLimit;

    @DecimalMin(value = "10", message = "输出限制最小为10Byte")
    private Integer outputLimit;


    @NotNull(message = "语言不得为空")
    private String language;

    private String judgePreference;

    /**
     * 特判代码
     */
    private String special;

    /**
     * 特判代码语言
     */
    private String specialLanguage;

    public String getSpecialLanguage() {
        return specialLanguage;
    }

    public void setSpecialLanguage(String specialLanguage) {
        this.specialLanguage = specialLanguage;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    @NotNull(message = "期望输入、输出不得为空")
    @Size(message = "期望输入、输出长度最小为1", min = 1)
    @Valid
    private List<SolutionDTO> solutions;

    public Integer getRealTimeLimit() {
        return realTimeLimit == null ? JudgeConfigDefaultEnum.TIME_LIMIT_DEFAULT.getData() : realTimeLimit;
    }

    public Integer getOutputLimit() {
        return outputLimit == null ? JudgeConfigDefaultEnum.OUTPUT_LIMIT_DEFAULT.getData() : outputLimit;
    }

    public Integer getCpuTimeLimit() {
        return cpuTimeLimit == null ? JudgeConfigDefaultEnum.WALL_TIME_DEFAULT.getData() : cpuTimeLimit;
    }

    public Integer getMemoryLimit() {
        return memoryLimit == null ? JudgeConfigDefaultEnum.MEMORY_LIMIT_DEFAULT.getData() : memoryLimit;
    }

    /**
     * 是否acm模式
     *
     * @return boolean 是否是acm模式
     * @author yuzhanglong
     * @date 2020-7-2 22:31
     */
    public Boolean isAcmMode() {
        if (JudgePreferenceEnum.toJudgePreference(getJudgePreference()) == null) {
            throw new NotFoundException("A0005");
        }
        return getJudgePreference().equals(JudgePreferenceEnum.ACM.getPreference());
    }

    public String getJudgePreference() {
        return judgePreference == null ? JudgePreferenceEnum.ACM.getPreference() : judgePreference;
    }


    @Override
    public String toString() {
        return "JudgeDTO{" +
                "submissionCode='" + submissionCode + '\'' +
                ", realTimeLimit=" + realTimeLimit +
                ", cpuTimeLimit=" + cpuTimeLimit +
                ", memoryLimit=" + memoryLimit +
                ", outputLimit=" + outputLimit +
                ", language='" + language + '\'' +
                ", resolutions=" + solutions +
                '}';
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSubmissionCode() {
        return submissionCode;
    }

    public void setSubmissionCode(String submissionCode) {
        this.submissionCode = submissionCode;
    }


    public void setRealTimeLimit(Integer realTimeLimit) {
        this.realTimeLimit = realTimeLimit;
    }


    public void setMemoryLimit(Integer memoryLimit) {
        this.memoryLimit = memoryLimit;
    }


    public void setCpuTimeLimit(Integer cpuTimeLimit) {
        this.cpuTimeLimit = cpuTimeLimit;
    }


    public void setOutputLimit(Integer outputLimit) {
        this.outputLimit = outputLimit;
    }

    public List<SolutionDTO> getSolutions() {
        return solutions;
    }

    public void setSolutions(List<SolutionDTO> solutions) {
        this.solutions = solutions;
    }


    public void setJudgePreference(String judgePreference) {
        this.judgePreference = judgePreference;
    }
}
