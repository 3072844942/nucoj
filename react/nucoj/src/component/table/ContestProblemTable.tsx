import React, {useEffect, useState} from 'react';
import {connect} from "react-redux";
import {ProblemInfo} from "../../interface/problem";
import {Table, Tag} from "antd";
import {NavLink} from "react-router-dom";
import {UserProblemInfo} from "../../interface/contest";
import axios from "axios";

function ContestProblemTable(props: {
    data:ProblemInfo[],
    contestId: string,
    userId: string
}) {

    const [data, setData] = useState<UserProblemInfo[]>()

    const getStatus = (index:number) => {
        if (data === undefined) return null
        if (data[index].accept) return <Tag color={'success'}>通过</Tag>
        if (data[index].wrong) return <Tag color={'default'}>尝试</Tag>
        return null
    }

    useEffect(() => {
        if (props.userId !== '') {
            axios({
                url: '/api/contest/state/user',
                method: 'post',
                data: {
                    id: props.userId,
                    contestId: props.contestId
                }
            }).then(res => {
                if (res.data.data !== null)
                    setData(res.data.data.states)
            })
        }
    }, [props.userId])

    return (
        <Table<ProblemInfo> dataSource={props.data} pagination={false}>
            <Table.Column<ProblemInfo> key="id" title="题号" dataIndex="id" align={'center'}/>
            <Table.Column<ProblemInfo>
                key="title"
                title='题目'
                dataIndex="title"
                align={'center'}
                width={'76%'}
                render={(text:string, record: ProblemInfo) => <NavLink to={'/contest/' + props.contestId +'/problem/' + record.id}>{text}</NavLink>}
            />
            <Table.Column<ProblemInfo> key="rate" title="通过率" dataIndex="rate" align={'center'}/>
            <Table.Column<ProblemInfo> key="state" title="我的状态" dataIndex="state" align={'center'}
                                       render={(_, record, index) => getStatus(index)}/>
        </Table>
    );
}

const mapStateToProps = (state:any, preState:{
    data:ProblemInfo[],
    contestId: string
}) => {
    return {
        ...preState,
        userId: state.UserInfo.id
    }
}

export default connect(mapStateToProps)(ContestProblemTable);