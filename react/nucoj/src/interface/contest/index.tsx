/**
 * 简易比赛信息
 * 用于主页近期比赛表格
 */
import {ProblemInfo} from "../problem";

interface ContestInfo {
    id: string,
    title: string,
    startTime: number,
    time: number,
    author: string,
    number: number,
    context: string,
    entry: boolean,
    problems: ProblemInfo[]
}

interface ColumnInfo {
    problemId: string,
    rate: string
}

interface UserProblemInfo {
    punish: number,
    wrong: number,
    accept: boolean
}

interface UserRankInfo {
    userId: string,
    name: string,
    number: string,
    states: UserProblemInfo[]
}

interface RankInfo {
    columns: ColumnInfo[]
    data: UserRankInfo[],
    total: number
}

interface SubmitInfo {
    code: string,
    condition: number,
    id: string,
    language: string,
    problemId: string,
    time: number,
    user: string
}

export type {ContestInfo, RankInfo, ColumnInfo, UserRankInfo, UserProblemInfo, SubmitInfo}