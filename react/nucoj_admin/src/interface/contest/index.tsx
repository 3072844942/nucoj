import {ProblemInfo} from "../problem";

/**
 * 简易比赛信息
 * 用于主页近期比赛表格
 */
interface ContestInfo {
    id: string,
    title: string,
    startTime: number,
    time: number,
    context: string
    author: string,
    problems: ProblemInfo[]
}

export type {ContestInfo}