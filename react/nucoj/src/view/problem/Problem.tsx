import React, {useEffect, useState} from 'react';
import {connect} from "react-redux";
import {UserInfo} from "../../interface/user";
import {useNavigate, useParams} from "react-router-dom";
import axios from "axios";
import {ExampleInfo, ProblemInfo} from "../../interface/problem";
import {SUCCESS} from "../../constant/controller";
import ProblemContextContainer from "../../component/container/ProblemContextContainer";
import Editor from '../../component/editor/index'
import {Button} from "antd";

/**
 * 题目详情
 * @constructor
 */
function Problem(props: { userInfo: UserInfo }) {
    const params = useParams();
    const [data, setData] = useState<ProblemInfo>({
        id: '',
        title: '',
        time: 0,
        grade: 0,
        tags: [],
        context: '',
        inputContext: '',
        outputContext: '',
        timeRequire: 0,
        memoryRequire: 0,
        examples: [],
        rate: '',
        state: false
    })
    const history = useNavigate()

    useEffect(() => {
        axios({
            url: params.contestId === undefined
                ? '/api/problem/' + params.problemId
                : '/api/contest/' + params.contestId + '/problem/' + params.problemId,
            method: 'post'
        }).then(res => {
            if (res.data.code === SUCCESS) setData(res.data.data)
            // else history('/404')
        })
    }, [])

    useEffect(() => {
        document.title = data?.title + ' | 题目'
    }, [data])


    return (
        <div style={{
            width: '100%',
            display: 'flex',
        }}>
            <div
                style={{
                    width: '40%',
                    height: '97vh',
                    margin: '1vw 0 0 2vw'
                }}
            >
                <div style={{position: 'absolute', top: 0, left: 0, color: 'white'}}>
                    <Button type={'link'} ghost onClick={() => history(-1)}>返回</Button>
                </div>
                <ProblemContextContainer data={data}></ProblemContextContainer>
            </div>
            <div
                style={{
                    width: '60%',
                    height: '100vh',
                    overflowY: 'hidden'
                }}
            >
                <Editor contestId={params.contestId} problem={data} userId={props.userInfo.id} problemId={params.problemId}/>
            </div>
        </div>
    );
}

const mapStateToProps = (state: any) => {
    return {
        userInfo: state.UserInfo
    }
}

const mapDispatchToProps = {}

export default connect(mapStateToProps, mapDispatchToProps)(Problem);