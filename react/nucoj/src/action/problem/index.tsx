import axios from "axios";
import toast from "../../component/toast";
import ErrorIcon from "../../assets/icon/ErrorIcon";
import CorrectIcon from "../../assets/icon/CorrectIcon";
import React from "react";
import {SUCCESS} from "../../constant/controller";

const judge = (code: string, language: string, userId: string, problemId: string, contestId: string | undefined, setAnswer: Function, setShow: Function) => {
    setAnswer('judging')
    axios({
        url: contestId === undefined ? '/api/problem/submit' : '/api/contest/problem/submit',
        method: 'post',
        data: {
            language: language,
            id: problemId,
            userId: userId,
            contestId: contestId,
            code: code
        }
    }).then(res => {
        // 这里有一个更复杂的逻辑
        if (res.data.data.judgeResults[0].condition == 16) { // 编辑错误
            let tmp = ""
            for (let i = 0; i < res.data.data.extraInfo.length; i ++ ) {
                tmp += res.data.data.extraInfo[i] + "\n"
            }
            setAnswer(tmp)
            setShow('answer')
            toast('compile error', '', <ErrorIcon/>, 'bottom', 0)
        }
        else if (res.data.data.judgeResults.at(res.data.data.judgeResults.length - 1).condition == 1) { // 答案错误
            toast('wrong answer', 'wrong answer on ' + res.data.data.judgeResults.length, <ErrorIcon/>, 'bottom', 10)
        }
        else if (res.data.data.judgeResults.at(res.data.data.judgeResults.length - 1).condition == 2) { // 运行时错误
            toast('runtime error', 'runtime error on ' + res.data.data.judgeResults.length, <ErrorIcon/>, 'bottom', 10)
        }
        else if (res.data.data.judgeResults.at(res.data.data.judgeResults.length - 1).condition == 3) { // 运行超时
            toast('time limit exceeded', 'time limit exceeded on' + res.data.data.judgeResults.length, <ErrorIcon/>, 'bottom', 10)
        }
        else if (res.data.data.judgeResults.at(res.data.data.judgeResults.length - 1).condition == 6) { // 内存超限
            toast('segmentation fail', 'memory limit exceeded on ' + res.data.data.judgeResults.length, <ErrorIcon/>, 'bottom', 10)
        }
        else if (res.data.data.judgeResults.at(res.data.data.judgeResults.length - 1).condition == 0){
            toast('accept', '', <CorrectIcon/>, 'bottom')
        }
        else toast('fail', res.data.data.judgeResults.at(res.data.data.judgeResults.length - 1).message, <CorrectIcon/>, 'bottom')
    })
}

const debug = (timeRequire: number | undefined, language:string, code: string, test: string, set:Function) => {
    axios({
        url: '/api/problem/debug',
        method: 'post',
        data: {
            language: language,
            code: code,
            test: test
        }
    }).then(res => {
        if (res.data.code === SUCCESS) set(res.data.data)
        else set(res.data.message)
    })
}

export {
    judge,
    debug
}