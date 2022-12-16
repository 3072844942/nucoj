

interface ExampleInfo {
    test: string,
    answer: string
}

/**
 * 完整题目信息
 */
interface ProblemInfo {
    id: string,
    title: string,
    time: number,
    grade: number | string,
    tags: string[],
    context: string,
    inputContext: string,
    outputContext: string,
    timeRequire: number,
    memoryRequire: number,
    examples: ExampleInfo[],
    rate: string,
    state: boolean
}

export type {ProblemInfo, ExampleInfo}