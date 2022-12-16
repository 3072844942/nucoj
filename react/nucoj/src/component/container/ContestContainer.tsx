import React, {useEffect, useState} from 'react';
import {ContestInfo} from "../../interface/contest";
import {Tabs} from "antd";
import {markdownToHtml} from "../../util";
import moment from "moment/moment";
import ContestProblemTable from "../table/ContestProblemTable";
import ContestRankTable from "../table/ContestRankTable";
import ContestSubmitTable from "../table/ContestSubmitTable";

/**
 * 比赛容器
 * 比赛页面下面主内容
 * @param props
 * @constructor
 */
function ContestContainer(props: { data: ContestInfo, style?: object, contestId: string}) {
    const [activeKey, setActiveKey] = useState("problems")
    const [time, setTime] = useState(props.data.startTime + props.data.time - moment().utc().valueOf() - 32 * 60 * 60 * 1000);
    const items = [
        {
            label: '比赛说明',
            key: 'context',
            children: <div
                dangerouslySetInnerHTML={{__html: markdownToHtml(props.data.context)}}
                style={{
                    margin: '0 auto',
                    padding: '1vh 1vw',
                    borderRadius: '10px',
                    backgroundColor: '#EEE'
                }}
            ></div>
        },
        {
            label: '题目列表',
            key: 'problems',
            children: <ContestProblemTable contestId={props.contestId} data={props.data.problems}/>
        },
        {
            label: '提交列表',
            key: 'submit',
            children: <ContestSubmitTable contestId={props.contestId}/>
        },
        {
            label: '排名',
            key: 'rank',
            children: <ContestRankTable contestId={props.contestId}/>
        }
    ];

    useEffect(() => {
        if (time > -32 * 60 * 60 * 1000) {
            const timer = setTimeout(() => {
                let newTime = time - 1000
                setTime(newTime)
                clearTimeout(timer)
            }, 1000)
        }
    }, [time])

    return (
        <Tabs
            style={props.style}
            type="card"
            activeKey={activeKey}
            onChange={(activeKey:string) => {
                setActiveKey(activeKey)
            }}
            items={items}
            tabBarGutter={8}
            tabBarExtraContent={
                time > -32 * 60 * 60 * 1000 ? <p>距离比赛结束：{moment(time).format("DD 天 HH 时 mm 分 ss 秒").toString()}</p>:<p>已结束</p>
            }
        />
    );
}

export default ContestContainer;