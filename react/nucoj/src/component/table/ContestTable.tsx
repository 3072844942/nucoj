import React from 'react';
import {Button, Table, Tag} from "antd";
import {NavLink} from "react-router-dom";
import {ContestInfo} from "../../interface/contest";
import {getDate, getTime} from "../../util";
import dayjs from "dayjs";
import {CheckCircleOutlined, MinusCircleOutlined, SyncOutlined} from "@ant-design/icons";
import axios from "axios";
import {SUCCESS} from "../../constant/controller";
import toast from "../toast";
import ErrorIcon from "../../assets/icon/ErrorIcon";

/**
 * 主页最近比赛表格
 * @constructor
 */
function ContestTable(props: {
    data:ContestInfo[],
    entryList: string[],
    userId: string
}) {

    const getStatus = (startTime: number, time: number, contestId: string) => {
        let now = dayjs().valueOf()

        if (startTime > now) {
            if (props.entryList.includes(contestId)) return <Tag icon={<CheckCircleOutlined />} color="success">已报名</Tag>
            else return <Button
                type="dashed"
                onClick={() => {
                    axios({
                        url: '/api/contest/entry',
                        method: 'post',
                        data: {
                            id: props.userId,
                            contestId: contestId
                        }
                    }).then(res => {
                        if (res.data.code === SUCCESS) toast('success', '')
                        else toast('fail', res.data.message, <ErrorIcon/>)
                    })
                }}
            >报名</Button>
        } else {
            if (startTime + time < now) return <Tag icon={<MinusCircleOutlined/>} color="default">stop</Tag>
            else return <Tag icon={<SyncOutlined spin/>} color="processing">processing</Tag>
        }
    }


    return (
        <Table<ContestInfo> dataSource={props.data} pagination={false}>
            <Table.Column<ContestInfo> key="id" title='编号' dataIndex="id" align={'center'}/>
            <Table.Column<ContestInfo> key="title" title="名称" dataIndex="title" align={'center'}
                                       render={(text: string, record: ContestInfo) =>
                                           <NavLink to={'/contest/' + record.id}>{text}</NavLink>}/>
            <Table.Column<ContestInfo> key="startTime" title="开始时间" dataIndex="startTime" align={'center'}
                                       render={text => getDate(text)}/>
            <Table.Column<ContestInfo> key="time" title="时长" dataIndex="time" align={'center'}
                                       render={text => getTime(text)}/>
            <Table.Column<ContestInfo> key="author" title="发布人" dataIndex="author" align={'center'}/>
            <Table.Column<ContestInfo> key="status" title="#" dataIndex="status" align={'center'} width={'5vw'}
                                       render={(text: string, record: ContestInfo) => getStatus(record.startTime, record.time, record.id)}/>
        </Table>
    );
}

export default ContestTable;