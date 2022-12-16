
import {ProblemInfo} from "../problem";

interface TrainInfo {
    id: string,
    title: string,
    author: string,
    // completedQuantity: number,
    // quantity: number,
    problems: ProblemInfo[]
}

export type {TrainInfo}