import React from 'react';
import {TableParams} from "../../interface/table";
import {LogInfo} from "../../interface/log";
import {Button, Popconfirm, Table, Tag} from "antd";
import type {ColumnsType} from 'antd/es/table';
import {getDate} from "../../util";
import TextContainer from "../container/TextContainer";

/**
 * 日志表格
 * @param props
 * @constructor
 */
function LogTable(props: {
    data: LogInfo[],
    tableParams: TableParams,
    setTableParams: Function,
    handleTableChange: Function,
    del: Function
}) {
    const columns: ColumnsType<LogInfo> = [
        {
            title: '操作路径',
            dataIndex: 'optUrl',
            key: 'optUrl',
            align: 'center'
        },
        {
            title: '操作方式',
            dataIndex: 'requestMethod',
            key: 'requestMethod',
            align: 'center',
            render: text => <Tag color="magenta">{text}</Tag>
        },
        {
            title: '操作人员',
            dataIndex: 'user',
            key: 'user',
            align: 'center'
        },
        {
            title: '操作时间',
            dataIndex: 'createTime',
            key: 'createTime',
            align: 'center',
            render: text => getDate(text)
        },
        {
            title: 'Action',
            dataIndex: '',
            key: 'x',
            align: 'center',
            render: (_, record) =>
                <Button type={'link'} onClick={() => props.del(record.id)}>删除</Button>,
        },
    ];

    return (
        <Table
            columns={columns}
            expandable={{
                // 提示无用但不可删除
                expandedRowRender: record => <div>
                    <TextContainer width={'100%'} style={{margin: 0}} title={'request'}>{record.requestParam}</TextContainer>
                    <TextContainer width={'100%'} style={{margin: 0}} title={'response'}>{record.responseData}</TextContainer>
                </div>,
                rowExpandable: record => record.user !== 'Not Expandable'
            }}
            dataSource={props.data}
            sticky={true}
            bordered={true}
            pagination={props.tableParams.pagination}
            // @ts-ignore
            onChange={props.handleTableChange}
        />
    );
}

export default LogTable;