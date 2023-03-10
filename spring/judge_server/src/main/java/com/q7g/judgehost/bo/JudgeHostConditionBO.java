package com.q7g.judgehost.bo;

/**
 * 描述判题服务器状态的业务对象
 */
public class JudgeHostConditionBO {
    private Integer workingAmount;
    private Integer cpuCoreAmount;
    private Integer memoryCostPercentage;
    private Integer cpuCostPercentage;
    private Integer queueAmount;
    private Integer maxWorkingAmount;

    public JudgeHostConditionBO(
            Integer workingAmount,
            Integer cpuCoreAmount,
            Integer memoryCostPercentage,
            Integer cpuCostPercentage,
            Integer queueAmount,
            Integer maxWorkingAmount) {
        this.workingAmount = workingAmount;
        this.cpuCoreAmount = cpuCoreAmount;
        this.memoryCostPercentage = memoryCostPercentage;
        this.cpuCostPercentage = cpuCostPercentage;
        this.queueAmount = queueAmount;
        this.maxWorkingAmount = maxWorkingAmount;
    }

    public Integer getQueueAmount() {
        return queueAmount;
    }

    public void setQueueAmount(Integer queueAmount) {
        this.queueAmount = queueAmount;
    }

    public Integer getWorkingAmount() {
        return workingAmount;
    }

    public void setWorkingAmount(Integer workingAmount) {
        this.workingAmount = workingAmount;
    }

    public Integer getCpuCoreAmount() {
        return cpuCoreAmount;
    }

    public void setCpuCoreAmount(Integer cpuCoreAmount) {
        this.cpuCoreAmount = cpuCoreAmount;
    }

    public Integer getMemoryCostPercentage() {
        return memoryCostPercentage;
    }

    public void setMemoryCostPercentage(Integer memoryCostPercentage) {
        this.memoryCostPercentage = memoryCostPercentage;
    }

    public Integer getCpuCostPercentage() {
        return cpuCostPercentage;
    }

    public void setCpuCostPercentage(Integer cpuCostPercentage) {
        this.cpuCostPercentage = cpuCostPercentage;
    }

    public Integer getMaxWorkingAmount() {
        return maxWorkingAmount;
    }

    public void setMaxWorkingAmount(Integer maxWorkingAmount) {
        this.maxWorkingAmount = maxWorkingAmount;
    }

    @Override
    public String toString() {
        return "JudgeHostConditionBO{" +
                "workingAmount=" + workingAmount +
                ", cpuCoreAmount=" + cpuCoreAmount +
                ", memoryCostPercentage=" + memoryCostPercentage +
                ", cpuCostPercentage=" + cpuCostPercentage +
                '}';
    }
}
