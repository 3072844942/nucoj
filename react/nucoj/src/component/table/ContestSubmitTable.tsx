import React, {useEffect, useState} from 'react';
import {connect} from "react-redux";
import {RankInfo, SubmitInfo, UserRankInfo} from "../../interface/contest";
import {TableParams} from "../../interface/table";
import axios from "axios";
import {SUCCESS} from "../../constant/controller";
import {ColumnsType, TablePaginationConfig} from "antd/es/table";
import {FilterValue, SorterResult} from "antd/es/table/interface";
import {Button, Table, Tag} from "antd";
import {getDate} from "../../util";
import SubmitInfoContainer from "../container/SubmitInfoContainer";

function ContestSubmitTable(props: {
    contestId: string
}) {
    const [data, setData] = useState<SubmitInfo[]>()
    const [tableParams, setTableParams] = useState<TableParams>({
        pagination: {
            current: 1,
            pageSize: 10,
        },
    });
    const [show, setShow] = useState<SubmitInfo>()

    const columns:ColumnsType<SubmitInfo> = [
        {
            key: 'id',
            title: '编号',
            dataIndex: 'id',
            align: 'center',
            width: '7%',
            render: (value, record) => <Button type={'link'} onClick={() => setShow(record)}>
                {value}
            </Button>
        },
        {
            key: 'user',
            title: '用户',
            dataIndex: 'user',
            align: 'center',
            width: '7%',
        },
        {
            key: 'problemId',
            title: '题号',
            dataIndex: 'problemId',
            align: 'center',
            width: '7%',
        },
        {
            key: 'condition',
            title: '运行结果',
            dataIndex: 'condition',
            align: 'center',
            width: '7%',
            render: value => getStatus(value)
        },
        {
            key: 'language',
            title: '使用语言',
            dataIndex: 'language',
            align: 'center',
            width: '7%',
        },
        {
            key: 'time',
            title: '提交时间',
            dataIndex: 'time',
            align: 'center',
            width: '7%',
            render: value => getDate(value)
        },
    ]

    const getStatus = (condition: number) => {
        if (condition == 0) return <Tag color={'success'}>通过</Tag>
        if (condition == 1) return <Tag color={'error'}>答案错误</Tag>
        if (condition == 2) return <Tag color={'volcano'}>运行时错误</Tag>
        if (condition == 3) return <Tag color={'geekblue'}>超时</Tag>
        if (condition == 6) return <Tag color={'purple'}>内存超限</Tag>
        if (condition == 16) return <Tag color={'magenta'}>编辑错误</Tag>
        return <Tag color={'default'}>未知</Tag>
    }

    const getData = () => {
        axios({
            url: '/api/contest/submit/record',
            method: 'post',
            data: {
                current: tableParams.pagination?.current,
                size: tableParams.pagination?.pageSize,
                id: props.contestId
            },
            timeout: 2000
        }).then(res => {
            if (res.data.code === SUCCESS) {
                setData(res.data.data.list)
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

    useEffect(() => {
        getData()
    }, [JSON.stringify(tableParams)])

    return (
        <div>
            {
                show !== undefined ? <SubmitInfoContainer data={show} onClick={() => setShow(undefined)}/>:null
            }
            <Table
                columns={columns}
                dataSource={data}
                sticky
                bordered
                pagination={tableParams.pagination}
                // @ts-ignore
                onChange={handleTableChange}
            />
        </div>


    );
}
export default ContestSubmitTable;