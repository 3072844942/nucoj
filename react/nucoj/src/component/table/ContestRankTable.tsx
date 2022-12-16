import React, {useEffect, useState} from 'react';
import {connect} from "react-redux";
import {RankInfo, UserProblemInfo, UserRankInfo} from "../../interface/contest";
import {TableParams} from "../../interface/table";
import {FilterValue, SorterResult} from "antd/es/table/interface";
import {ColumnsType, TablePaginationConfig} from "antd/es/table";
import axios from "axios";
import {SUCCESS} from "../../constant/controller";
import {Button, Table, Tag} from "antd";

function ContestRankTable(props: {
    contestId: string,
    userId: string
}) {
    const [data, setData] = useState<RankInfo>()
    const [tableParams, setTableParams] = useState<TableParams>({
        pagination: {
            current: 1,
            pageSize: 10,
        },
    });

    const getDate = () => {
        axios({
            url: '/api/contest/rank',
            method: 'post',
            data: {
                current: tableParams.pagination?.current,
                size: tableParams.pagination?.pageSize,
                id: props.contestId,
                userId: props.userId
            },
            timeout: 2000
        }).then(res => {
            if (res.data.code === SUCCESS) {
                setData(res.data.data)
                setTableParams({
                    ...tableParams,
                    pagination: {
                        ...tableParams.pagination,
                        total: res.data.data.total,
                        // 200 is mock data, you should read it from server
                        // total: data.totalCount,
                    },
                });
            }
        })
    }

    const handleTableChange = (
        pagination: TablePaginationConfig,
        filters: Record<string, FilterValue>,
        sorter: SorterResult<RankInfo>
    ) => {
        setTableParams({
            pagination,
            filters,
            ...sorter,
        });
    };

    const getPunish = (record: UserProblemInfo[]) => {
        let sum = 0;
        record.forEach(item => sum += item.punish)
        return Math.floor(sum / 1000 / 60)
    }

    // @ts-ignore
    // 类型不匹配， 但实际上是满足要求的
    const mergeColumns:ColumnsType<UserRankInfo> = [
        {
            key: 'index',
            title: '排名',
            dataIndex: 'index',
            render: (value: any, record: any, index: any) => {
                return index + 1
            },
            align: 'center',
            width: '7%',
            fixed: 'left'
        },
        {
            key: 'name',
            title: '用户',
            dataIndex: 'name',
            align: 'center',
            width: '10%',
            fixed: 'left'
        },
        {
            key: 'number',
            title: '学号',
            dataIndex: 'number',
            align: 'center',
            width: '10%',
            fixed: 'left'
        },
        {
            key: 'punish',
            title: '罚时',
            dataIndex: 'punish',
            align: 'center',
            width: '10%',
            fixed: 'left',
            render: (value: any, record: UserRankInfo, index: any) => getPunish(record.states)
        }
        // @ts-ignore
        // 拼接上题目栏
    ].concat(data === undefined ? [] : data?.columns.map((item, index) => {
        return {
            key: item.problemId,
            title: item.problemId,
            dataIndex: item.problemId,
            align: 'center',
            render: (value:any, record:UserRankInfo) => <Tag color={record.states[index].accept ? 'success' : 'error'}>
                {record.states[index].accept ? record.states[index].wrong : -record.states[index].wrong}
                &nbsp;/ {Math.floor(record.states[index].punish / 1000 / 60)}
            </Tag>
        }
    }))

    useEffect(() => {
        getDate()
    }, [JSON.stringify(tableParams)])

    return (
        <div>
            <Table
                columns={mergeColumns}
                dataSource={data?.data}
                sticky
                bordered
                pagination={tableParams.pagination}
                // @ts-ignore
                onChange={handleTableChange}
            />
            <Button style={{position: 'relative', top: -50}} onClick={getDate}>重新加载</Button>
        </div>

    );
}

const mapStatToProps = (state:any, preState:{
    contestId: string
}) => {
    return {
        ...preState,
        userId: state.UserInfo.id
    }
}

export default connect(mapStatToProps)(ContestRankTable);