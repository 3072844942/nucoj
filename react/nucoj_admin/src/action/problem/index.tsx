import axios from "axios";
import toast from "../../component/toast";
import ErrorIcon from "../../assets/icon/ErrorIcon";
import CorrectIcon from "../../assets/icon/CorrectIcon";
import React from "react";

const judge = (code: string, language: string, userId: string, problemId: string | undefined, setAnswer: Function, setShow: Function) => {
    axios({
        url: '/api/problem/submit',
        method: 'post',
        data: {
            language: language,
            problemId: problemId,
            userId: userId,
            code: code
        }
    }).then(res => {
        // 这里有一个更复杂的逻辑
        if (res.data.data.judgeResults[0].condition == 16) { // 编辑错误
            let tmp = ""
            res.data.data.extraInfo.map((item:string) => tmp += item + '\n')
            setAnswer(tmp)
            setShow('answer')
            toast('compile error', '', <ErrorIcon/>)
        }
        else if (res.data.data.judgeResults.at(res.data.data.judgeResults.length - 1).condition == 1) { // 答案错误
            toast('wrong answer', 'wrong answer on' + res.data.data.judgeResults.length, <ErrorIcon/>)
        }
        else if (res.data.data.judgeResults.at(res.data.data.judgeResults.length - 1).condition == 3) { // 运行超时
            toast('time limit exceeded', 'time limit exceeded on' + res.data.data.judgeResults.length, <ErrorIcon/>)
        }
        else if (res.data.data.judgeResults.at(res.data.data.judgeResults.length - 1).condition == 6) { // 内存超限
            toast('segmentation fail', '', <ErrorIcon/>)
        }
        else {
            toast('accept', '', <CorrectIcon/>)
        }
    })
}

const debug = (language:string, code: string, test: string, set:Function) => {
    axios({
        url: '/api/problem/debug',
        method: 'post',
        data: {
            language: language,
            code: code,
            test: test
        }
    }).then(res => {
        if (res.data.data.judgeResults[0].condition == 0) set(res.data.data.judgeResults[0].answer)
        else {
            let tmp = ""
            res.data.data.extraInfo.map((item:string) => tmp += item)
            set(tmp)
        }
    })
}

export {
    judge,
    debug
}