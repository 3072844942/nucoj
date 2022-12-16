

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
    grade: string,
    tags: string[],
    context: string,
    inputContext: string,
    outputContext: string,
    timeRequire: number | null,
    memoryRequire: number | null,
    examples: ExampleInfo[],
    recordId: string,
    isSpecial: boolean,
    specialJudge: string,
    specialLanguage: string
}

export type {ProblemInfo, ExampleInfo}