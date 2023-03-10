package com.q7g.judgehost.core.enumeration;

/**
 * 判题配置枚举类
 * 一些判题配置的默认值，如果用户没有传入，则会使用这些默认值
 */

public enum JudgeConfigDefaultEnum {
    // cpu时间限制 4000ms
    TIME_LIMIT_DEFAULT(4000),

    // 内存限制 1024 * 32 kb = 32mb
    MEMORY_LIMIT_DEFAULT(1024 * 32),

    // 实际时间限制 4000ms
    WALL_TIME_DEFAULT(4000),

    // 进程限制
    PROCESS_LIMIT_DEFAULT(1),

    // 输出限制
    OUTPUT_LIMIT_DEFAULT(1000000);

    private final Integer data;

    JudgeConfigDefaultEnum(Integer data) {
        this.data = data;
    }

    public Integer getData() {
        return data;
    }
}
